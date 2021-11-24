package com.wizeline.bootcamp.capstone.data.services

import com.wizeline.bootcamp.capstone.data.mock.OrderBookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OrderBookService {
    @GET("order_book/")
    suspend fun getOrderBookByBook(
        @Query("book") book: String,
        @Query("aggregate") aggregate: Boolean
    ): Response<OrderBookResponse>
}