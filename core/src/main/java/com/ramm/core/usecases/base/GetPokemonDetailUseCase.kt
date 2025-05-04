package com.ramm.core.usecases.base

import com.ramm.core.domain.PokemonDetailUseCaseInfo
import com.ramm.core.domain.Result
import com.ramm.core.usecases.SuspendUseCase
import kotlinx.coroutines.flow.Flow

interface GetPokemonDetailUseCase : SuspendUseCase<Int, PokemonDetailUseCaseInfo> {
    override suspend fun invoke(param: Int): Result<PokemonDetailUseCaseInfo>
}