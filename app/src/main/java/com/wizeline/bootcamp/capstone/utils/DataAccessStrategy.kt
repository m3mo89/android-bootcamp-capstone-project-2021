package com.wizeline.bootcamp.capstone.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.wizeline.bootcamp.capstone.data.NetworkResult
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> NetworkResult<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<NetworkResult<T>> =
    liveData(Dispatchers.IO) {
        emit(NetworkResult.Loading())
        val source: LiveData<NetworkResult<T>> =
            databaseQuery.invoke().map { NetworkResult.Success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()

        if (responseStatus is NetworkResult.Success) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus is NetworkResult.Error) {
            emit(NetworkResult.Error(responseStatus.message!!))
            emitSource(source)
        }
    }
