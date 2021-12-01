package com.wizeline.bootcamp.capstone.data.services

import com.wizeline.bootcamp.capstone.data.mock.AvailableBooksResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface AvailableBooksService {
    @GET("available_books")
    fun getAvailableBooks(): Observable<AvailableBooksResponse>
}
