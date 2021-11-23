package com.wizeline.bootcamp.capstone.data.repo

import androidx.lifecycle.map
import com.wizeline.bootcamp.capstone.data.local.TickerDAO
import com.wizeline.bootcamp.capstone.data.mapper.fromLocal
import com.wizeline.bootcamp.capstone.data.mapper.toLocal
import com.wizeline.bootcamp.capstone.data.remote.TickerRemoteDataSource
import com.wizeline.bootcamp.capstone.utils.performGetOperation

class TickerRepo(
    private val remoteDataSource: TickerRemoteDataSource,
    private val localDataSource: TickerDAO
) {
    fun getTickerByBook(book: String) = performGetOperation(
        databaseQuery =
        {
            val ticker = localDataSource.getTickerByBook(book)

            ticker.map {
                it.let { it.fromLocal() }
            }
        },
        networkCall = { remoteDataSource.getTickerByBook(book) },
        saveCallResult = { it ->
            val payload = it.payload

            if (payload != null) {
                localDataSource.insert(payload.toLocal())
            }
        }
    )
}