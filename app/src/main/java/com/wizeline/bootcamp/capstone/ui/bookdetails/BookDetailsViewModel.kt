package com.wizeline.bootcamp.capstone.ui.bookdetails

import androidx.lifecycle.*
import com.wizeline.bootcamp.capstone.data.NetworkResult
import com.wizeline.bootcamp.capstone.data.mapper.OrderBookResponseMapper
import com.wizeline.bootcamp.capstone.data.repo.OrderBookRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO
import com.wizeline.bootcamp.capstone.domain.TickerDTO
import com.wizeline.bootcamp.capstone.utils.Constants.Companion.ERROR_MESSAGE
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val tickerRepo: TickerRepo,
    private val orderBookRepo: OrderBookRepo,
    private val orderbookMapper: OrderBookResponseMapper,
) : ViewModel() {
    private var _orderBook: MutableLiveData<NetworkResult<OrderBookDTO>> =
        MutableLiveData<NetworkResult<OrderBookDTO>>()
    val orderBook: LiveData<NetworkResult<OrderBookDTO>> = _orderBook

    private val _book = MutableLiveData<String>()

    private val _ticker = _book.switchMap { book ->
        tickerRepo.getTickerByBook(book)
    }

    val ticker: LiveData<NetworkResult<TickerDTO>> = _ticker

    fun requestData(book: String) {
        _book.value = book
    }

    fun requestOrderBookData(book: String) {
        viewModelScope.launch {
            _orderBook.postValue(NetworkResult.Loading())

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
                    _orderBook.postValue(NetworkResult.Error(ERROR_MESSAGE))
                }
            } catch (e: Exception) {
                _orderBook.postValue(NetworkResult.Error(e.message))
            }
        }
    }
}