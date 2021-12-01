package com.wizeline.bootcamp.capstone.data.remote

import com.wizeline.bootcamp.capstone.data.services.AvailableBooksService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRemoteDataSource @Inject constructor (
    private val availableBooksService: AvailableBooksService
) : BaseDataSource() {
    suspend fun getAvailableBooks() = getResult {
        availableBooksService.getAvailableBooks()
    }
}
