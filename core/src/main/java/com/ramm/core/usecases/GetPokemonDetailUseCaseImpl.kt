package com.ramm.core.usecases

import com.ramm.core.repositories.PokemonRepository
import com.ramm.core.usecases.base.GetPokemonDetailUseCase

class GetPokemonDetailUseCaseImpl(
    private val repository: PokemonRepository
) : GetPokemonDetailUseCase {
    override suspend fun invoke(param: Int) = repository.getPokemonDetail(param)
}