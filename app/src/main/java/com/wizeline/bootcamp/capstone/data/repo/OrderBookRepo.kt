package com.wizeline.bootcamp.capstone.data.repo

import com.wizeline.bootcamp.capstone.data.services.OrderBookService
import com.wizeline.bootcamp.capstone.di.NetworkingModule

class OrderBookRepo ( private val orderBookService:OrderBookService) {
    suspend fun getOrderBookByBookAndAggregate(book: String, aggregate: Boolean) =
        orderBookService.getOrderBookByBookAndAggregate(book, aggregate)
}