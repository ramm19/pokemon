package com.ramm.framework.data.repositories

import com.ramm.core.repositories.PokemonRepository
import com.ramm.framework.data.repositories.base.BaseRepository
import com.ramm.framework.service.PokemonService
import com.ramm.framework.utils.getData

class PokemonRepositoryImpl(
    private val pokemonService: PokemonService
) : BaseRepository(), PokemonRepository {
    override suspend fun getAllPokemonFirstGeneration() = fetchData {
        pokemonService.getAllPokemonFirstGeneration().getData()
    }

    override suspend fun getPokemonDetail(pokemonId: Int) = fetchData {
        pokemonService.getPokemonDetail(pokemonId).getData()
    }
}