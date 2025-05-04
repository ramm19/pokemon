package com.ramm.core.usecases

import com.ramm.core.repositories.PokemonRepository
import com.ramm.core.usecases.base.GetAllPokemonFirstGenerationUseCase

class GetAllPokemonFirstGenerationUseCaseImpl(
    private val repository: PokemonRepository
) : GetAllPokemonFirstGenerationUseCase {
    override suspend fun invoke() = repository.getAllPokemonFirstGeneration()
}