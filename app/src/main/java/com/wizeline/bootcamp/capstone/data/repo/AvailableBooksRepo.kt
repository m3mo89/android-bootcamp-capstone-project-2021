package com.wizeline.bootcamp.capstone.data.repo

import androidx.lifecycle.map
import com.wizeline.bootcamp.capstone.data.local.BookDAO
import com.wizeline.bootcamp.capstone.data.mapper.fromLocal
import com.wizeline.bootcamp.capstone.data.mapper.toLocal
import com.wizeline.bootcamp.capstone.data.remote.BookRemoteDataSource
import com.wizeline.bootcamp.capstone.utils.performGetOperation

class AvailableBooksRepo(
    private val remoteDataSource: BookRemoteDataSource,
    private val localDataSource: BookDAO
) {
    fun getAvailableBooks() = performGetOperation(
        databaseQuery =
        {
            localDataSource.getAll().map {
                it.map { book ->
                    book.fromLocal()
                }
            }
        },
        networkCall = { remoteDataSource.getAvailableBooks() },
        saveCallResult = { it ->
            val payload = it.payload

            if (payload != null) {
                localDataSource.insertAll(payload.map { it.toLocal() })
            }
        }
    )
}