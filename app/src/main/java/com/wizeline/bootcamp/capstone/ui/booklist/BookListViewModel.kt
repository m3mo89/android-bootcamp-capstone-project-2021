package com.wizeline.bootcamp.capstone.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.bootcamp.capstone.data.NetworkResult
import com.wizeline.bootcamp.capstone.data.entities.BookEntity
import com.wizeline.bootcamp.capstone.data.local.BookDAO
import com.wizeline.bootcamp.capstone.data.mapper.AvailableBookResponseMapper
import com.wizeline.bootcamp.capstone.data.mapper.toDTO
import com.wizeline.bootcamp.capstone.data.mapper.toLocal
import com.wizeline.bootcamp.capstone.data.mock.AvailableBooksResponse
import com.wizeline.bootcamp.capstone.data.remote.BookRemoteDataSource
import com.wizeline.bootcamp.capstone.domain.BookDTO
import com.wizeline.bootcamp.capstone.utils.Constants.Companion.ERROR_MESSAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val localDataSource: BookDAO,
    private val availableBookResponseMapper: AvailableBookResponseMapper
) : ViewModel() {
    private var _result: MutableLiveData<NetworkResult<List<BookDTO>>> =
        MutableLiveData<NetworkResult<List<BookDTO>>>()
    val result: LiveData<NetworkResult<List<BookDTO>>> = _result

    fun requestLocalData() {
        viewModelScope.launch {
            _result.postValue(NetworkResult.Loading())

            try {
                val booksEntities = localDataSource.getAll()

                val booksDTO = availableBookResponseMapper.mapList(booksEntities)

                val networkResult = NetworkResult.Success(booksDTO)

                _result.postValue(networkResult)
            } catch (error: Exception) {
                _result.postValue(NetworkResult.Error(error.message))
            }
        }
    }

    fun requestRemoteData() {
        remoteDataSource.getAvailableBooks()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(handleResponse())
    }

    private fun handleResponse(): Observer<AvailableBooksResponse> {
        return object : Observer<AvailableBooksResponse> {
            override fun onSubscribe(d: Disposable) {
                _result.postValue(NetworkResult.Loading())
            }

            override fun onNext(response: AvailableBooksResponse) {
                val success = response.success ?: false

                if (success) {
                    val payload = response.payload

                    if (payload != null) {
                        val listOfBooks = payload.map { it.toDTO() }
                        val listOfEntities = payload.map { it.toLocal() }

                        _result.postValue(NetworkResult.Success(listOfBooks))

                        viewModelScope.launch {
                            saveCallResult(listOfEntities)
                        }
                    }
                } else {
                    _result.postValue(NetworkResult.Error(ERROR_MESSAGE))
                }
            }

            override fun onError(error: Throwable) {
                error.let {
                    _result.postValue(NetworkResult.Error(error.message))
                }
            }

            override fun onComplete() {

            }
        }
    }

    suspend fun saveCallResult(entities: List<BookEntity>) {
        localDataSource.insertAll(entities)
    }
}
