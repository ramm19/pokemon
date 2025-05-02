package com.ramm.core.usecases.base

import com.ramm.core.domain.PokemonUseCaseInfo
import com.ramm.core.domain.Result
import com.ramm.core.usecases.SuspendNoParameterUseCase


interface GetAllPokemonFirstGenerationUseCase : SuspendNoParameterUseCase<PokemonUseCaseInfo> {
    override suspend fun invoke(): Result<PokemonUseCaseInfo>
}