package com.ramm.core.domain

data class PokemonUseCaseInfo(
    val pokemonEntries: List<PokemonEntriesUseCaseInfo>
)

data class PokemonEntriesUseCaseInfo(
    val entryNumber: Int,
    val pokemonSpecies: PokemonSpeciesUseCaseInfo
)

data class PokemonSpeciesUseCaseInfo(
    val name: String,
    var urlImage: String
)
