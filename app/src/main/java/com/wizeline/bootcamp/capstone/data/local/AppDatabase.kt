package com.wizeline.bootcamp.capstone.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wizeline.bootcamp.capstone.data.converters.AskConverter
import com.wizeline.bootcamp.capstone.data.converters.BidConverter
import com.wizeline.bootcamp.capstone.data.entities.BookEntity
import com.wizeline.bootcamp.capstone.data.entities.OrderBookEntity
import com.wizeline.bootcamp.capstone.data.entities.TickerEntity
import com.wizeline.bootcamp.capstone.utils.TableNames

@Database(
    entities = [BookEntity::class, TickerEntity::class, OrderBookEntity::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(AskConverter::class, BidConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDAO

    abstract fun tickerDao(): TickerDAO

    abstract fun orderBookDao(): OrderBookDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "${TableNames.DATABASE_NAME}")
                .fallbackToDestructiveMigration()
                .build()
    }
}
