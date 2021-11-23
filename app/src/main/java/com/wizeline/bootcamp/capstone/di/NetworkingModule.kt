package com.wizeline.bootcamp.capstone.di

import android.app.Application
import com.wizeline.bootcamp.capstone.data.local.AppDatabase
import com.wizeline.bootcamp.capstone.data.local.BookDAO
import com.wizeline.bootcamp.capstone.data.local.TickerDAO
import com.wizeline.bootcamp.capstone.data.remote.BookRemoteDataSource
import com.wizeline.bootcamp.capstone.data.remote.TickerRemoteDataSource
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo
import com.wizeline.bootcamp.capstone.data.services.AvailableBooksService
import com.wizeline.bootcamp.capstone.data.services.OrderBookService
import com.wizeline.bootcamp.capstone.data.services.TickerService
import com.wizeline.bootcamp.capstone.utils.Constants
import com.wizeline.bootcamp.capstone.utils.Constants.Companion.USER_AGENT_KEY
import com.wizeline.bootcamp.capstone.utils.Constants.Companion.USER_AGENT_VALUE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .client(client())
            .build()
    }

    private fun client(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(USER_AGENT_KEY, USER_AGENT_VALUE)
                    .build()
                chain.proceed(newRequest)
            }
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

    fun provideAvailableBooksRepository(
        remoteDataSource: BookRemoteDataSource,
        localDataSource: BookDAO
    ) =
        AvailableBooksRepo(remoteDataSource, localDataSource)

    fun provideTickerRemoteDataSource(tickerService: TickerService) =
        TickerRemoteDataSource(tickerService)

    fun provideTickerDao(db: AppDatabase) =
        db.tickerDao()

    fun provideTickerRepository(
        remoteDataSource: TickerRemoteDataSource,
        localDataSource: TickerDAO
    ) =
        TickerRepo(remoteDataSource, localDataSource)
}