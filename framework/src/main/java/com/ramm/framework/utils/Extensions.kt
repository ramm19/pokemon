package com.ramm.framework.utils

import android.util.Log
import com.ramm.core.domain.DataSourceError
import com.ramm.core.domain.Failure
import com.ramm.core.domain.Result
import com.ramm.core.domain.Success
import com.ramm.framework.data.mapper.DomainMapper
import retrofit2.Response
import java.io.IOException

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (DataSourceError) -> Unit) {
    if (!isSuccessful) errorBody()?.run{}
}

fun <RESPONSE : DomainMapper<INFO>, INFO : Any> Response<RESPONSE>.getData(): Result<INFO> {
    try {
        onSuccess {
            Log.d("ramm", "result success")
            return Success(it.mapToDomainModel())
        }
        onFailure {
            Log.d("ramm", "result failure")
            return Failure(it)
        }
        return Failure(DataSourceError(Throwable(GENERAL_ERROR)))
    } catch (e: IOException) {
        return Failure(DataSourceError(Throwable(GENERAL_ERROR)))
    }
}