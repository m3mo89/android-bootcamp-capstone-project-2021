package com.wizeline.bootcamp.capstone.data.remote

import com.wizeline.bootcamp.capstone.data.services.TickerService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TickerRemoteDataSource @Inject constructor(
    private val tickerService: TickerService
) {
    fun getTickerByBook(book: String) =
        tickerService.getTickerByBook(book)
}
