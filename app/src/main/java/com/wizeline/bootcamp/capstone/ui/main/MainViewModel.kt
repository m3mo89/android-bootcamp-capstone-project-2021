package com.wizeline.bootcamp.capstone.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    fun loadData() {
        viewModelScope.launch {
            val repo:AvailableBooksRepo = AvailableBooksRepo()
            val response = repo.getAvailableBooks().body()

            val payload = response!!.payload

            payload.forEach {
                println("Book: ${it.book}")
            }
        }
    }

    fun loadTickerData() {
        viewModelScope.launch {
            val repo:TickerRepo = TickerRepo()
            val response = repo.getTickerService("ltc_usd").body()

            val payload = response!!.payload

            println("Book: ${payload.book} Volume: ${payload.volume}")
        }
    }
}