package com.wizeline.bootcamp.capstone.ui.booklist

import androidx.lifecycle.ViewModel
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val availableBooksRepo: AvailableBooksRepo
) : ViewModel() {
    val result = availableBooksRepo.getAvailableBooks()
}
