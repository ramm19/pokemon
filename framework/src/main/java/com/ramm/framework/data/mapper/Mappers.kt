package com.ramm.framework.data.mapper

interface DomainMapper<T: Any> {
    fun mapToDomainModel(): T
}