package com.wizeline.bootcamp.capstone.data.repo

import androidx.lifecycle.map
import com.wizeline.bootcamp.capstone.data.local.BookDAO
import com.wizeline.bootcamp.capstone.data.mapper.fromLocal
import com.wizeline.bootcamp.capstone.data.mapper.toLocal
import com.wizeline.bootcamp.capstone.data.remote.BookRemoteDataSource
import com.wizeline.bootcamp.capstone.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AvailableBooksRepo @Inject constructor(
    private val remoteDataSource: BookRemoteDataSource,
    private val localDataSource: BookDAO
) {
    fun getAvailableBooks() = performGetOperation(
        databaseQuery =
        {
            val books = localDataSource.getAll()

            books.let {
                books.map { book ->
                    book.map { it.fromLocal() }
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