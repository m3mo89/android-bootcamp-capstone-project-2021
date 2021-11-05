package com.wizeline.bootcamp.capstone.data.repo

import com.wizeline.bootcamp.capstone.data.services.OrderBookService

class OrderBookRepo ( private val orderBookService:OrderBookService) {
    suspend fun getOrderBookByBookAndAggregate(book: String, aggregate: Boolean) =
        orderBookService.getOrderBookByBookAndAggregate(book, aggregate)
}