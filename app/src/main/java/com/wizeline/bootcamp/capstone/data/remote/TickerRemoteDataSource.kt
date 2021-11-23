package com.wizeline.bootcamp.capstone.data.remote

import com.wizeline.bootcamp.capstone.data.services.TickerService

class TickerRemoteDataSource(
    private val tickerService: TickerService
) : BaseDataSource() {
    suspend fun getTickerByBook(book: String) = getResult {
        tickerService.getTickerByBook(book)
    }
}