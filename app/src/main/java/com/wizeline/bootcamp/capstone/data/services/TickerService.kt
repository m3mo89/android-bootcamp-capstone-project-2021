package com.wizeline.bootcamp.capstone.data.services

import com.wizeline.bootcamp.capstone.data.mock.TickerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TickerService {
    @GET("ticker/")
    suspend fun getTickerByBook(@Query("book") book: String): Response<TickerResponse>
}