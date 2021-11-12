package com.wizeline.bootcamp.capstone.ui.bookdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.bootcamp.capstone.data.NetworkResult
import com.wizeline.bootcamp.capstone.data.mapper.OrderBookResponseMapper
import com.wizeline.bootcamp.capstone.data.mapper.TickerResponseMapper
import com.wizeline.bootcamp.capstone.data.repo.OrderBookRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO
import com.wizeline.bootcamp.capstone.domain.Ticker
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val tickerRepo: TickerRepo,
    private val orderBookRepo: OrderBookRepo,
    private val tickerMapper: TickerResponseMapper,
    private val orderbookMapper: OrderBookResponseMapper,
) : ViewModel() {
    private var _ticker: MutableLiveData<NetworkResult<Ticker>> =
        MutableLiveData<NetworkResult<Ticker>>()
    val ticker: LiveData<NetworkResult<Ticker>> = _ticker

    private var _orderBook: MutableLiveData<NetworkResult<OrderBookDTO>> =
        MutableLiveData<NetworkResult<OrderBookDTO>>()
    val orderBook: LiveData<NetworkResult<OrderBookDTO>> = _orderBook

    fun requestData(book: String) {
        viewModelScope.launch {
            _ticker.postValue(NetworkResult.Loading())

            try {
                val response = tickerRepo.getTickerByBook(book).body()
                val success = response?.success ?: false

                if (success) {
                    val payload = response?.payload

                    if (payload != null) {
                        val ticker = tickerMapper.map(payload)

                        _ticker.postValue(NetworkResult.Success(ticker))
                    }
                } else {
                    _ticker.postValue(NetworkResult.Error("Unknown issue"))
                }
            } catch (e: Exception) {
                _ticker.postValue(NetworkResult.Error(e.message))
            }
        }
    }

    fun requestOrderBookData(book: String) {
        viewModelScope.launch {
            _ticker.postValue(NetworkResult.Loading())

            try {
                val response = orderBookRepo.getOrderBookByBookAndAggregate(book, true).body()
                val success = response?.success ?: false

                if (success) {
                    val payload = response?.payload

                    if (payload != null) {
                        val orderBook = orderbookMapper.map(payload)

                        _orderBook.postValue(NetworkResult.Success(orderBook))
                    }
                } else {
                    _orderBook.postValue(NetworkResult.Error("Unknown issue"))
                }
            } catch (e: Exception) {
                _orderBook.postValue(NetworkResult.Error(e.message))
            }
        }
    }
}