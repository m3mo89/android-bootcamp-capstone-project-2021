package com.wizeline.bootcamp.capstone.data.remote

import com.wizeline.bootcamp.capstone.data.services.OrderBookService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderBookRemoteDataSource @Inject constructor (
    private val orderBookService: OrderBookService
) {
    fun getOrderBookByBook(book: String) =
        orderBookService.getOrderBookByBook(book, true)
}
