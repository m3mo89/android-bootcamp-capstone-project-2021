package com.wizeline.bootcamp.capstone.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.bootcamp.capstone.data.NetworkResult
import com.wizeline.bootcamp.capstone.data.mapper.AvailableBookResponseMapper
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo
import com.wizeline.bootcamp.capstone.domain.Book
import kotlinx.coroutines.launch

class BookListViewModel(
    private val availableBooksRepo: AvailableBooksRepo,
    private val booksMapper: AvailableBookResponseMapper,
) : ViewModel() {
    private var _result: MutableLiveData<NetworkResult<List<Book>>> =
        MutableLiveData<NetworkResult<List<Book>>>()
    val result: LiveData<NetworkResult<List<Book>>> = _result

    fun requestData() {
        viewModelScope.launch {
            _result.postValue(NetworkResult.Loading())

            try {
                val response = availableBooksRepo.getAvailableBooks().body()

                val success = response?.success ?: false

                if (success) {
                    val payload = response?.payload

                    if (payload != null) {
                        val listOfBooks = booksMapper.mapList(payload)

                        _result.postValue(NetworkResult.Success(listOfBooks))
                    }
                } else {
                    _result.postValue(NetworkResult.Error("Unknown issue"))
                }
            } catch (e: Exception) {
                _result.postValue(NetworkResult.Error(e.message))
            }
        }
    }
}