package com.ramm.core.domain

data class PokemonUseCaseInfo(
    val pokemonEntries: List<PokemonEntriesUseCaseInfo>
)

data class PokemonEntriesUseCaseInfo(
    val entryNumber: Int,
    var urlImage: String,
    val pokemonSpecies: PokemonSpeciesUseCaseInfo
)

data class PokemonSpeciesUseCaseInfo(
    val name: String
)
