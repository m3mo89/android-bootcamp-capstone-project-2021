package com.wizeline.bootcamp.capstone.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wizeline.bootcamp.capstone.data.entities.BookEntity
import com.wizeline.bootcamp.capstone.utils.TableNames

@Dao
interface BookDAO {
    @Query("SELECT * FROM ${TableNames.BOOKS} ORDER BY book DESC")
    suspend fun getAll(): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookEntity>)
}
