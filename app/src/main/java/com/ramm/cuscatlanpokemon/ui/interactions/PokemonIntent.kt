package com.ramm.cuscatlanpokemon.ui.interactions

import com.ramm.core.domain.PokemonDetailUseCaseInfo
import com.ramm.core.domain.PokemonEntriesUseCaseInfo

sealed class PokemonIntent {
    sealed class Screen : PokemonIntent() {
        object GetDataProfile : Screen()
        object SaveImageProfile: Screen()
        object SaveDataProfile : Screen()
        object GetAllPokemonFirstGeneration : Screen()
        object DoSearchPokemon : Screen()
        data class GetDetailPokemon(val idPokemon: Int) : Screen()
        object EditFavoritePokemon : Screen()
    }

    sealed class Reduce : PokemonIntent() {
        data class SetSearch(val search: String) : Reduce()
        data class SetListPokemon(val listPokemon: List<PokemonEntriesUseCaseInfo>) : Reduce()
        data class SetListPokemonFilter(val listPokemonFilter: List<PokemonEntriesUseCaseInfo>) : Reduce()
        data class SetDetailPokemon(val detailPokemon: PokemonDetailUseCaseInfo) : Reduce()
        data class SetListFavoritePokemon(val listFavoritePokemon: List<Int>) : Reduce()
        data class SetName(val name: String) : Reduce()
        data class SetHobby(val hobby: String) : Reduce()
        data class SetBirthDate(val birthDate: String) : Reduce()
        data class SetDocument(val document: String) : Reduce()
        data class SetImagePath(val imagePath: String) : Reduce()
        data class SetSelectedPokemon(val selectedPokemon: PokemonEntriesUseCaseInfo) : Reduce()
    }
}