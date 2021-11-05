package com.wizeline.bootcamp.capstone.data.repo

import com.wizeline.bootcamp.capstone.data.services.AvailableBooksService

class AvailableBooksRepo(private val availableBooksService: AvailableBooksService) {
    suspend fun getAvailableBooks() =
        availableBooksService.getAvailableBooks()
}