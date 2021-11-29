package com.wizeline.bootcamp.capstone.data.remote

import com.wizeline.bootcamp.capstone.data.NetworkResult
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    return NetworkResult.Success(body)
                }
            }

            return NetworkResult.Error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
}