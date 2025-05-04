package com.ramm.core.usecases

import com.ramm.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface SuspendUseCase<T: Any, R: Any> {
    suspend operator fun invoke(param: T): Result<R>
}

interface SuspendNoParameterUseCase<R: Any> {
    suspend operator fun invoke(): Result<R>
}
