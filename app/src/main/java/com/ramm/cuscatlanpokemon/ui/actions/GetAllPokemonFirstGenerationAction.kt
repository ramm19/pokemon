package com.ramm.cuscatlanpokemon.ui.actions

import android.util.Log
import com.ramm.core.domain.Failure
import com.ramm.core.domain.PokemonEntriesUseCaseInfo
import com.ramm.core.domain.Success
import com.ramm.core.usecases.base.GetAllPokemonFirstGenerationUseCase
import com.ramm.cuscatlanpokemon.preferences
import com.ramm.cuscatlanpokemon.ui.base.Action
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel

class GetAllPokemonFirstGenerationAction(
    private val viewModel: PokemonViewModel,
    private val getAllPokemon : GetAllPokemonFirstGenerationUseCase
) : Action {
    override suspend fun execute() {
        when (val result = getAllPokemon()) {
            is Failure -> {
                //todo mostrar error
            }
            is Success -> {
                Log.d("ramm", "success: $result.data")
                viewModel.onIntent(PokemonIntent.Reduce.SetListPokemon(result.data.pokemonEntries))
                viewModel.onIntent(PokemonIntent.Reduce.SetListPokemonFilter(result.data.pokemonEntries))

                val favorites = preferences.myFavorites
                val listFavorites : List<Int> = if (favorites.isBlank())
                    emptyList()
                else
                    favorites.split(",").map { it.toInt() }

                val listFavoritesData = mutableListOf<PokemonEntriesUseCaseInfo>()
                listFavorites.forEach { idPokemon ->
                    viewModel.currentState.listPokemon.firstOrNull() { pokemon ->
                        pokemon.entryNumber == idPokemon
                    }?.let {
                        listFavoritesData.add(it)
                    }
                }

                viewModel.onIntent(PokemonIntent.Reduce.SetListFavoritePokemon(listFavorites, listFavoritesData))
            }
        }
    }
}