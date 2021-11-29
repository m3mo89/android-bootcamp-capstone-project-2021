package com.wizeline.bootcamp.capstone.data.remote

import com.wizeline.bootcamp.capstone.data.services.TickerService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TickerRemoteDataSource @Inject constructor(
    private val tickerService: TickerService
) : BaseDataSource() {
    suspend fun getTickerByBook(book: String) = getResult {
        tickerService.getTickerByBook(book)
    }
}