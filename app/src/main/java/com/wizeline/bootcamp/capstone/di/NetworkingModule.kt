package com.wizeline.bootcamp.capstone.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BITSO_API_BASE_URL = "https://api.bitso.com/v3/"

object NetworkingModule {
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BITSO_API_BASE_URL)
            .build()
    }
}