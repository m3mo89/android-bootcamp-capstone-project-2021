package com.wizeline.bootcamp.capstone.di

import android.app.Application
import com.wizeline.bootcamp.capstone.data.local.AppDatabase
import com.wizeline.bootcamp.capstone.data.local.BookDAO
import com.wizeline.bootcamp.capstone.data.local.OrderBookDAO
import com.wizeline.bootcamp.capstone.data.local.TickerDAO
import com.wizeline.bootcamp.capstone.data.remote.BookRemoteDataSource
import com.wizeline.bootcamp.capstone.data.remote.OrderBookRemoteDataSource
import com.wizeline.bootcamp.capstone.data.remote.TickerRemoteDataSource
import com.wizeline.bootcamp.capstone.data.repo.AvailableBooksRepo
import com.wizeline.bootcamp.capstone.data.repo.OrderBookRepo
import com.wizeline.bootcamp.capstone.data.repo.TickerRepo
import com.wizeline.bootcamp.capstone.data.services.AvailableBooksService
import com.wizeline.bootcamp.capstone.data.services.OrderBookService
import com.wizeline.bootcamp.capstone.data.services.TickerService
import com.wizeline.bootcamp.capstone.utils.Constants
import com.wizeline.bootcamp.capstone.utils.Constants.Companion.USER_AGENT_KEY
import com.wizeline.bootcamp.capstone.utils.Constants.Companion.USER_AGENT_VALUE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BITSO_API_BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun client(): OkHttpClient {
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

    @Provides
    @Singleton
    fun provideAvailableBooksService(retrofit: Retrofit): AvailableBooksService {
        return retrofit.create(AvailableBooksService::class.java)
    }

    @Provides
    @Singleton
    fun provideTickerService(retrofit: Retrofit): TickerService {
        return retrofit.create(TickerService::class.java)
    }

    @Provides
    @Singleton
    fun provideOrderBookService(retrofit: Retrofit): OrderBookService {
        return retrofit.create(OrderBookService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(appContext: Application) =
        AppDatabase.getDatabase(appContext)

    @Provides
    @Singleton
    fun provideBookRemoteDataSource(availableBookService: AvailableBooksService) =
        BookRemoteDataSource(availableBookService)

    @Provides
    @Singleton
    fun provideBookDao(db: AppDatabase) =
        db.bookDao()

    @Provides
    @Singleton
    fun provideAvailableBooksRepository(
        remoteDataSource: BookRemoteDataSource,
        localDataSource: BookDAO
    ) =
        AvailableBooksRepo(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideTickerRemoteDataSource(tickerService: TickerService) =
        TickerRemoteDataSource(tickerService)

    @Provides
    @Singleton
    fun provideTickerDao(db: AppDatabase) =
        db.tickerDao()

    @Provides
    @Singleton
    fun provideTickerRepository(
        remoteDataSource: TickerRemoteDataSource,
        localDataSource: TickerDAO
    ) =
        TickerRepo(remoteDataSource, localDataSource)

    @Provides
    @Singleton
    fun provideOrderBookRemoteDataSource(orderBookService: OrderBookService) =
        OrderBookRemoteDataSource(orderBookService)

    @Provides
    @Singleton
    fun provideOrderBookDao(db: AppDatabase) =
        db.orderBookDao()

    @Provides
    @Singleton
    fun provideOrderBookRepository(
        remoteDataSource: OrderBookRemoteDataSource,
        localDataSource: OrderBookDAO
    ) =
        OrderBookRepo(remoteDataSource, localDataSource)
}