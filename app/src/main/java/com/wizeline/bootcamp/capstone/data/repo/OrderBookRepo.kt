package com.wizeline.bootcamp.capstone.data.repo

import com.wizeline.bootcamp.capstone.data.services.OrderBookService
import com.wizeline.bootcamp.capstone.di.NetworkingModule

class OrderBookRepo {
    suspend fun getOrderBookByBookAndAggregate(book: String, aggregate: Boolean) = NetworkingModule
        .provideRetrofitClient()
        .create(OrderBookService::class.java)
        .getOrderBookByBookAndAggregate(book, aggregate)
}