package com.wizeline.bootcamp.capstone.data.remote

import com.wizeline.bootcamp.capstone.data.services.OrderBookService

class OrderBookRemoteDataSource(
    private val orderBookService: OrderBookService
) : BaseDataSource() {
    suspend fun getOrderBookByBook(book: String) = getResult {
        orderBookService.getOrderBookByBook(book, true)
    }
}