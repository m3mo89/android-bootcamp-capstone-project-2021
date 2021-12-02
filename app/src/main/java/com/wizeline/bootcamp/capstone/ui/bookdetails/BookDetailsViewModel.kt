package com.wizeline.bootcamp.capstone.ui.bookdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.bootcamp.capstone.data.NetworkResult
import com.wizeline.bootcamp.capstone.data.api.OrderBookResponse
import com.wizeline.bootcamp.capstone.data.api.TickerResponse
import com.wizeline.bootcamp.capstone.data.entities.OrderBookEntity
import com.wizeline.bootcamp.capstone.data.entities.TickerEntity
import com.wizeline.bootcamp.capstone.data.local.OrderBookDAO
import com.wizeline.bootcamp.capstone.data.local.TickerDAO
import com.wizeline.bootcamp.capstone.data.mapper.OrderBookResponseMapper
import com.wizeline.bootcamp.capstone.data.mapper.TickerResponseMapper
import com.wizeline.bootcamp.capstone.data.mapper.toDTO
import com.wizeline.bootcamp.capstone.data.mapper.toLocal
import com.wizeline.bootcamp.capstone.data.remote.OrderBookRemoteDataSource
import com.wizeline.bootcamp.capstone.data.remote.TickerRemoteDataSource
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO
import com.wizeline.bootcamp.capstone.domain.TickerDTO
import com.wizeline.bootcamp.capstone.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val orderBookRemoteDataSource: OrderBookRemoteDataSource,
    private val orderBookLocalDataSource: OrderBookDAO,
    private val tickerRemoteDataSource: TickerRemoteDataSource,
    private val tickerLocalDataSource: TickerDAO,
    private val tickerMapper: TickerResponseMapper,
    private val orderbookMapper: OrderBookResponseMapper
) : ViewModel() {
    private var _ticker: MutableLiveData<NetworkResult<TickerDTO>> =
        MutableLiveData<NetworkResult<TickerDTO>>()
    val ticker: LiveData<NetworkResult<TickerDTO>> = _ticker

    private var _orderBook: MutableLiveData<NetworkResult<OrderBookDTO>> =
        MutableLiveData<NetworkResult<OrderBookDTO>>()
    val orderBook: LiveData<NetworkResult<OrderBookDTO>> = _orderBook

    fun requestTickerLocalData(book: String) {
        viewModelScope.launch {
            _ticker.postValue(NetworkResult.Loading())

            try {
                val tickerEntity = tickerLocalDataSource.getTickerByBook(book)

                if (tickerEntity != null)
                {
                    val tickersDTO = tickerMapper.map(tickerEntity)

                    val networkResult = NetworkResult.Success(tickersDTO)

                    _ticker.postValue(networkResult)
                } else {
                    val emptyDTO = TickerDTO(
                        id = "",
                        spriteUrl = "",
                        cryptoName = "",
                        lastPrice = "",
                        highPrice = "",
                        lowPrice = "",
                        ask = "",
                        bid = "")

                    _ticker.postValue(NetworkResult.Success(emptyDTO))
                }
            } catch (error: Exception) {
                _ticker.postValue(NetworkResult.Error(error.message))
            }
        }
    }

    fun requestOrderBookLocalData(book: String) {
        viewModelScope.launch {
            _orderBook.postValue(NetworkResult.Loading())

            try {
                val orderBookEntity = orderBookLocalDataSource.getOrderBookByBook(book)

                if (orderBookEntity != null) {
                    val orderBooksDTO = orderbookMapper.map(orderBookEntity)

                    val networkResult = NetworkResult.Success(orderBooksDTO)

                    _orderBook.postValue(networkResult)
                } else {
                    val emptyDTO = OrderBookDTO(emptyList(), emptyList())

                    _orderBook.postValue(NetworkResult.Success(emptyDTO))
                }
            } catch (error: Exception) {
                _orderBook.postValue(NetworkResult.Error(error.message))
            }
        }
    }

    fun requestTickerRemoteData(book: String) {
        tickerRemoteDataSource.getTickerByBook(book)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(handleTickerResponse())
    }

    fun requestOrderBookRemoteData(book: String) {
        orderBookRemoteDataSource.getOrderBookByBook(book)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(handleOrderBookResponse(book))
    }

    private fun handleTickerResponse(): Observer<TickerResponse> {
        return object : Observer<TickerResponse> {
            override fun onSubscribe(d: Disposable) {
                _ticker.postValue(NetworkResult.Loading())
            }

            override fun onNext(response: TickerResponse) {
                val success = response.success ?: false

                if (success) {
                    val payload = response.payload

                    if (payload != null) {
                        val tickerDTO = payload.toDTO()
                        val tickerEntity = payload.toLocal()

                        _ticker.postValue(NetworkResult.Success(tickerDTO))

                        viewModelScope.launch {
                            saveTickerCallResult(tickerEntity)
                        }
                    }
                } else {
                    _ticker.postValue(NetworkResult.Error(Constants.ERROR_MESSAGE))
                }
            }

            override fun onError(error: Throwable) {
                error.let {
                    _ticker.postValue(NetworkResult.Error(error.message))
                }
            }

            override fun onComplete() {
            }
        }
    }

    private fun handleOrderBookResponse(book: String): Observer<OrderBookResponse> {
        return object : Observer<OrderBookResponse> {
            override fun onSubscribe(d: Disposable) {
                _orderBook.postValue(NetworkResult.Loading())
            }

            override fun onNext(response: OrderBookResponse) {
                val success = response.success ?: false

                if (success) {
                    val payload = response.payload

                    if (payload != null) {
                        val orderBookDTO = payload.toDTO()
                        val orderBookEntity = payload.toLocal(book)

                        _orderBook.postValue(NetworkResult.Success(orderBookDTO))

                        viewModelScope.launch {
                            saveOrderBookCallResult(orderBookEntity)
                        }
                    }
                } else {
                    _orderBook.postValue(NetworkResult.Error(Constants.ERROR_MESSAGE))
                }
            }

            override fun onError(error: Throwable) {
                error.let {
                    _orderBook.postValue(NetworkResult.Error(error.message))
                }
            }

            override fun onComplete() {
            }
        }
    }

    suspend fun saveTickerCallResult(tickerEntity: TickerEntity) {
        tickerLocalDataSource.insert(tickerEntity)
    }

    suspend fun saveOrderBookCallResult(orderBookEntity: OrderBookEntity) {
        orderBookLocalDataSource.insert(orderBookEntity)
    }
}
