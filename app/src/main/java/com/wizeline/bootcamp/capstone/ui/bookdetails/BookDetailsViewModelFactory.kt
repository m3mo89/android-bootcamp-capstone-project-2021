package com.wizeline.bootcamp.capstone.ui.bookdetails

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.wizeline.bootcamp.capstone.data.mapper.OrderBookAskResponseMapper
import com.wizeline.bootcamp.capstone.data.mapper.OrderBookBidResponseMapper
import com.wizeline.bootcamp.capstone.data.mapper.TickerResponseMapper
import com.wizeline.bootcamp.capstone.data.repo.OrderBookRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo

class BookDetailsViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val tickerRepo: TickerRepo,
    private val orderBookRepo: OrderBookRepo,
    private val tickerMapper: TickerResponseMapper,
    private val orderbookAskMapper: OrderBookAskResponseMapper,
    private val orderbookBidMapper: OrderBookBidResponseMapper,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String, modelClass: Class<T>, handle: SavedStateHandle
    ): T {
        return BookDetailsViewModel(
            tickerRepo,
            orderBookRepo,
            tickerMapper,
            orderbookAskMapper,
            orderbookBidMapper
        ) as T
    }
}