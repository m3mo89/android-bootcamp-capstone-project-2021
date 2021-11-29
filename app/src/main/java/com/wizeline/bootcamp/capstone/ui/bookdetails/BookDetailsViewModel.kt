package com.wizeline.bootcamp.capstone.ui.bookdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.wizeline.bootcamp.capstone.data.NetworkResult
import com.wizeline.bootcamp.capstone.data.repo.OrderBookRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo
import com.wizeline.bootcamp.capstone.domain.OrderBookDTO
import com.wizeline.bootcamp.capstone.domain.TickerDTO

class BookDetailsViewModel(
    private val tickerRepo: TickerRepo,
    private val orderBookRepo: OrderBookRepo,
) : ViewModel() {
    private val _bookForTicker = MutableLiveData<String>()
    private val _bookForOrderBook = MutableLiveData<String>()

    private val _ticker = _bookForTicker.switchMap { book ->
        tickerRepo.getTickerByBook(book)
    }

    private val _orderBook = _bookForOrderBook.switchMap { book ->
        orderBookRepo.getOrderBookByBook(book)
    }

    val ticker: LiveData<NetworkResult<TickerDTO>> = _ticker

    val orderBook: LiveData<NetworkResult<OrderBookDTO>> = _orderBook

    fun requestData(book: String) {
        _bookForTicker.value = book
        _bookForOrderBook.value = book
    }
}