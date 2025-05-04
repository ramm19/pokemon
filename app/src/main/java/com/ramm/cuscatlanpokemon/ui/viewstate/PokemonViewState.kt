package com.ramm.cuscatlanpokemon.ui.viewstate

import com.ramm.core.domain.PokemonDetailUseCaseInfo
import com.ramm.core.domain.PokemonEntriesUseCaseInfo

data class PokemonViewState(
    val search: String = "",
    val listPokemon: List<PokemonEntriesUseCaseInfo> = emptyList(),
    val listPokemonFilter: List<PokemonEntriesUseCaseInfo> = emptyList(),
    val selectedPokemon: PokemonEntriesUseCaseInfo? = null,
    val detailPokemon: PokemonDetailUseCaseInfo? = null,
    val listFavoritePokemon: List<Int> = emptyList(),
    val listFavoritePokemonData: List<PokemonEntriesUseCaseInfo> = emptyList(),
    val name: String = "",
    val hobby: String = "",
    val birthDate: String = "",
    val document: String = "",
    val imagePath: String = ""
)