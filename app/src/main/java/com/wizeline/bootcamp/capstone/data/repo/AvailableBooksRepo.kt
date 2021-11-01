package com.wizeline.bootcamp.capstone.data.repo

import com.wizeline.bootcamp.capstone.data.services.AvailableBooksService
import com.wizeline.bootcamp.capstone.di.NetworkingModule

class AvailableBooksRepo {
    suspend fun getAvailableBookService() = NetworkingModule
            .provideRetrofitClient()
            .create(AvailableBooksService::class.java)
            .getAvailableBooksService()
}