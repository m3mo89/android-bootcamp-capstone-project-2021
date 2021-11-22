package com.wizeline.bootcamp.capstone.di

import android.app.Application
import com.wizeline.bootcamp.capstone.data.local.AppDatabase
import com.wizeline.bootcamp.capstone.data.local.BookDAO
import com.wizeline.bootcamp.capstone.data.remote.BookRemoteDataSource
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo
import com.wizeline.bootcamp.capstone.data.services.AvailableBooksService
import com.wizeline.bootcamp.capstone.data.services.OrderBookService
import com.wizeline.bootcamp.capstone.data.services.TickerService
import com.wizeline.bootcamp.capstone.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkingModule {
    @Volatile
    lateinit var application: Application

    fun initializeDI(_application: Application) {
        if (!::application.isInitialized) {
            synchronized(this) {
                application = _application
            }
        }
    }

    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BITSO_API_BASE_URL)
            .build()
    }

    fun provideAvailableBooksService(retrofit: Retrofit): AvailableBooksService {
        return retrofit.create(AvailableBooksService::class.java)
    }

    fun provideTickerService(retrofit: Retrofit): TickerService {
        return retrofit.create(TickerService::class.java)
    }

    fun provideOrderBookService(retrofit: Retrofit): OrderBookService {
        return retrofit.create(OrderBookService::class.java)
    }

    fun provideDatabase(appContext: Application) =
        AppDatabase.getDatabase(appContext)

    fun provideBookRemoteDataSource(availableBookService: AvailableBooksService) =
        BookRemoteDataSource(availableBookService)

    fun provideBookDao(db: AppDatabase) =
        db.bookDao()

    fun provideRepository(
        remoteDataSource: BookRemoteDataSource,
        localDataSource: BookDAO
    ) =
        AvailableBooksRepo(remoteDataSource, localDataSource)
}