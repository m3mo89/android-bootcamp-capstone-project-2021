package com.wizeline.bootcamp.capstone.data.repo

import com.wizeline.bootcamp.capstone.data.services.AvailableBooksService
import com.wizeline.bootcamp.capstone.data.services.TickerService
import com.wizeline.bootcamp.capstone.di.NetworkingModule

class TickerRepo {
    suspend fun getTickerService(book: String) = NetworkingModule
        .provideRetrofitClient()
        .create(TickerService::class.java)
        .getTickerByBook(book)
}