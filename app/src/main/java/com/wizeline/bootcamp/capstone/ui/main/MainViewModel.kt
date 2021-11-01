package com.wizeline.bootcamp.capstone.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    fun loadData() {
        viewModelScope.launch {
            val repo:AvailableBooksRepo = AvailableBooksRepo()
            val response = repo.getAvailableBookService().body()

            val payload = response!!.payload

            payload.forEach {
                println("Book: ${it.book}")
            }

        }
    }
}