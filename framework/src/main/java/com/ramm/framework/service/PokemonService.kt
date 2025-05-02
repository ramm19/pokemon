package com.ramm.framework.service

import com.ramm.framework.data.network.PokemonDetailResponseData
import com.ramm.framework.data.network.PokemonResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("podedex/kanto")
    suspend fun getAllPokemonFirstGeneration() : Response<PokemonResponseData>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") pokemonId: Int
    ) : Response<PokemonDetailResponseData>
}