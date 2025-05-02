package com.ramm.core.repositories

import com.ramm.core.domain.PokemonDetailUseCaseInfo
import com.ramm.core.domain.PokemonUseCaseInfo
import com.ramm.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getAllPokemonFirstGeneration(): Result<PokemonUseCaseInfo>
    suspend fun getPokemonDetail(pokemonId: Int): Result<PokemonDetailUseCaseInfo>
}