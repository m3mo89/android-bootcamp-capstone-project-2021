package com.wizeline.bootcamp.capstone.data.remote

import com.wizeline.bootcamp.capstone.data.services.AvailableBooksService

class BookRemoteDataSource(
    private val availableBooksService: AvailableBooksService
) : BaseDataSource() {
    suspend fun getAvailableBooks() = getResult {
        availableBooksService.getAvailableBooks()
    }
}