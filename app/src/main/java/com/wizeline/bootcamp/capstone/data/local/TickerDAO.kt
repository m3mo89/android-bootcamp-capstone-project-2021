package com.wizeline.bootcamp.capstone.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wizeline.bootcamp.capstone.data.entities.TickerEntity
import com.wizeline.bootcamp.capstone.utils.TableNames

@Dao
interface TickerDAO {
    @Query("SELECT * FROM ${TableNames.TICKER} WHERE book=:book")
    fun getTickerByBook(book: String): LiveData<TickerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ticker: TickerEntity)
}
