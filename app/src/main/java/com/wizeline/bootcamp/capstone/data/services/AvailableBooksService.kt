package com.wizeline.bootcamp.capstone.data.services

import com.wizeline.bootcamp.capstone.data.api.AvailableBooksResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface AvailableBooksService {
    @GET("available_books")
    fun getAvailableBooks(): Observable<AvailableBooksResponse>
}
