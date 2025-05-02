package com.ramm.framework.data.network

import com.google.gson.annotations.SerializedName
import com.ramm.core.domain.PokemonEntriesUseCaseInfo
import com.ramm.core.domain.PokemonSpeciesUseCaseInfo
import com.ramm.core.domain.PokemonUseCaseInfo
import com.ramm.framework.data.mapper.DomainMapper

data class PokemonResponseData(
    @SerializedName("pokemon_entries")
    val pokemonEntries: List<PokemonEntriesResponseData>
) : DomainMapper<PokemonUseCaseInfo> {
    override fun mapToDomainModel() = PokemonUseCaseInfo(
        pokemonEntries.map { it.mapToDomainModel() }
    )
}

data class PokemonEntriesResponseData(
    @SerializedName("entry_number")
    val entryNumber: Int,
    @SerializedName("pokemon_species")
    val pokemonSpecies: PokemonSpeciesResponseData
) : DomainMapper<PokemonEntriesUseCaseInfo> {
    override fun mapToDomainModel() = PokemonEntriesUseCaseInfo (
        entryNumber = entryNumber,
        urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/${entryNumber}.png\"",
        pokemonSpecies = pokemonSpecies.mapToDomainModel()
    )
}

data class PokemonSpeciesResponseData(
    val name: String,
) : DomainMapper<PokemonSpeciesUseCaseInfo> {
    override fun mapToDomainModel() = PokemonSpeciesUseCaseInfo (
        name = name
    )
}
