package com.wizeline.bootcamp.capstone.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo
import com.wizeline.bootcamp.capstone.data.repo.OrderBookRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo
import com.wizeline.bootcamp.capstone.di.NetworkingModule
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val retrofitClient = NetworkingModule.provideRetrofitClient()
    private val availableBooksService =
        NetworkingModule.provideAvailableBooksService(retrofitClient)
    private val tickerService = NetworkingModule.provideTickerService(retrofitClient)
    private val orderBookService = NetworkingModule.provideOrderBookService(retrofitClient)

    fun loadAvailableBooksData() {
        viewModelScope.launch {

            val repo: AvailableBooksRepo = AvailableBooksRepo(availableBooksService)

            val response = repo.getAvailableBooks().body()

            val payload = response?.payload

            payload?.forEach {
                println("Book: ${it.book}")
            }
        }
    }

    fun loadTickerData() {
        viewModelScope.launch {
            val repo: TickerRepo = TickerRepo(tickerService)
            val response = repo.getTickerByBook("ltc_usd").body()

            val payload = response?.payload

            println("Book: ${payload?.book} Volume: ${payload?.volume}")
        }
    }

    fun loadOrderBookData() {
        viewModelScope.launch {
            val repo: OrderBookRepo = OrderBookRepo(orderBookService)
            val response = repo.getOrderBookByBookAndAggregate("ltc_usd", true).body()

            val payload = response?.payload

            println("Asks")

            payload?.asks?.forEach {
                println("Book: ${it.book} Price: ${it.price}")
            }

            println("Bids")

            payload?.bids?.forEach {
                println("Book: ${it.book} Price: ${it.price}")
            }

            println("UpdateAt: ${payload?.updatedAt} Sequence: ${payload?.sequence}")
        }
    }
}