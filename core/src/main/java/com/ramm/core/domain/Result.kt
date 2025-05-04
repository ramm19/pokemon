package com.ramm.core.domain

sealed class Result <out T : Any>

data class Success<out T : Any>(val data: T) : Result<T>()

data class Failure(val dataSourceError: DataSourceError) : Result<Nothing>()

class DataSourceError(throwable: Throwable) { val errorMessage = throwable.localizedMessage ?: "" }