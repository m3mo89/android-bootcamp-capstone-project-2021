package com.wizeline.bootcamp.capstone.data.services

import com.wizeline.bootcamp.capstone.data.mock.TickerResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TickerService {
    @GET("ticker/")
    fun getTickerByBook(@Query("book") book: String): Observable<TickerResponse>
}
