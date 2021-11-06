package com.wizeline.bootcamp.capstone.ui.bookdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.bootcamp.capstone.data.mapper.OrderBookAskResponseMapper
import com.wizeline.bootcamp.capstone.data.mapper.OrderBookBidResponseMapper
import com.wizeline.bootcamp.capstone.data.mapper.TickerResponseMapper
import com.wizeline.bootcamp.capstone.data.repo.OrderBookRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo
import com.wizeline.bootcamp.capstone.domain.Ask
import com.wizeline.bootcamp.capstone.domain.Bid
import com.wizeline.bootcamp.capstone.domain.Ticker
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val tickerRepo: TickerRepo,
    private val orderBookRepo: OrderBookRepo,
    private val tickerMapper: TickerResponseMapper,
    private val orderbookAskMapper: OrderBookAskResponseMapper,
    private val orderbookBidMapper: OrderBookBidResponseMapper
) : ViewModel() {
    private var _ticker: MutableLiveData<Ticker?> = MutableLiveData<Ticker?>()
    val ticker: LiveData<Ticker?> = _ticker

    private var _askList: MutableLiveData<List<Ask>?> = MutableLiveData<List<Ask>?>()
    val askList: LiveData<List<Ask>?> = _askList

    private var _bidList: MutableLiveData<List<Bid>?> = MutableLiveData<List<Bid>?>()
    val bidList: LiveData<List<Bid>?> = _bidList

    fun requestData(book: String) {
        viewModelScope.launch {
            val response = tickerRepo.getTickerByBook(book).body()

            val success = response?.success ?: false

            if (success) {
                val payload = response?.payload

                if (payload != null) {
                    val ticker = tickerMapper.map(payload)

                    _ticker.postValue(ticker)
                }
            }
        }
    }

    fun requestOrderBookData(book: String) {
        viewModelScope.launch {
            val response = orderBookRepo.getOrderBookByBookAndAggregate(book, true).body()

            val success = response?.success ?: false

            if (success) {
                val payload = response?.payload

                if (payload != null) {
                    val asks = orderbookAskMapper.mapList(payload.asks)

                    val bids = orderbookBidMapper.mapList(payload.bids)

                    _askList.postValue(asks)

                    _bidList.postValue(bids)
                }
            }
        }
    }
}