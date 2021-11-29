package com.wizeline.bootcamp.capstone.data.repo

import androidx.lifecycle.map
import com.wizeline.bootcamp.capstone.data.local.OrderBookDAO
import com.wizeline.bootcamp.capstone.data.mapper.fromLocal
import com.wizeline.bootcamp.capstone.data.mapper.toLocal
import com.wizeline.bootcamp.capstone.data.remote.OrderBookRemoteDataSource
import com.wizeline.bootcamp.capstone.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderBookRepo @Inject constructor(
    private val remoteDataSource: OrderBookRemoteDataSource,
    private val localDataSource: OrderBookDAO
) {
    fun getOrderBookByBook(book: String) = performGetOperation(
        databaseQuery =
        {
            val ordersBook = localDataSource.getOrderBookByBook(book)

            ordersBook.let {
                ordersBook.map { orderBook ->
                    orderBook.fromLocal()
                }
            }
        },
        networkCall = { remoteDataSource.getOrderBookByBook(book) },
        saveCallResult = { it ->
            val payload = it.payload

            if (payload != null) {
                localDataSource.insert(payload.toLocal(book))
            }
        }
    )
}