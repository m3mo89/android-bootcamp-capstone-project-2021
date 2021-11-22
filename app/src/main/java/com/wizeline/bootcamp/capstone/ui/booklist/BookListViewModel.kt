package com.wizeline.bootcamp.capstone.ui.booklist

import androidx.lifecycle.ViewModel
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo

class BookListViewModel(
    private val availableBooksRepo: AvailableBooksRepo
) : ViewModel() {
    val result = availableBooksRepo.getAvailableBooks()
}