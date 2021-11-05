package com.wizeline.bootcamp.capstone.data.repo

import com.wizeline.bootcamp.capstone.data.services.TickerService
import com.wizeline.bootcamp.capstone.di.NetworkingModule

class TickerRepo(private val tickerService:TickerService) {
    suspend fun getTickerByBook(book: String) =
        tickerService.getTickerByBook(book)
}