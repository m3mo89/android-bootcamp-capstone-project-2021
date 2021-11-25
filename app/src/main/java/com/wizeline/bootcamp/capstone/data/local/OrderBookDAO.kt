package com.wizeline.bootcamp.capstone.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wizeline.bootcamp.capstone.data.entities.OrderBookEntity
import com.wizeline.bootcamp.capstone.utils.TableNames

@Dao
interface OrderBookDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: OrderBookEntity)

    @Query("SELECT * FROM ${TableNames.ORDERS} WHERE book=:book")
    fun getOrderBookByBook(book: String): LiveData<OrderBookEntity>
}