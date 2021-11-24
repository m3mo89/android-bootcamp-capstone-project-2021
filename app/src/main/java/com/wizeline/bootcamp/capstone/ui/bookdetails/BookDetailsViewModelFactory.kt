package com.wizeline.bootcamp.capstone.ui.bookdetails

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.wizeline.bootcamp.capstone.data.repo.OrderBookRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo

class BookDetailsViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val tickerRepo: TickerRepo,
    private val orderBookRepo: OrderBookRepo,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String, modelClass: Class<T>, handle: SavedStateHandle
    ): T {
        return BookDetailsViewModel(
            tickerRepo,
            orderBookRepo,
        ) as T
    }
}