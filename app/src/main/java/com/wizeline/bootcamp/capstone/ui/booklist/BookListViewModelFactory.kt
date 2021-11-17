package com.wizeline.bootcamp.capstone.ui.booklist

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.wizeline.bootcamp.capstone.data.mapper.AvailableBookResponseMapper
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo

class BookListViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val availableBooksRepo: AvailableBooksRepo,
    private val booksMapper: AvailableBookResponseMapper,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String, modelClass: Class<T>, handle: SavedStateHandle
    ): T {
        return BookListViewModel(availableBooksRepo, booksMapper) as T
    }
}