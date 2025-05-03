package com.ramm.cuscatlanpokemon.ui.actions

import com.ramm.cuscatlanpokemon.preferences
import com.ramm.cuscatlanpokemon.ui.base.Action
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel
import com.ramm.cuscatlanpokemon.utils.MAX_FAVORITES

class EditFavoritePokemonAction(
    private val viewModel: PokemonViewModel
) : Action {
    override suspend fun execute() {
        val selectedPokemon = viewModel.currentState.detailPokemon
        val listFavorites = viewModel.currentState.listFavoritePokemon.toMutableList()

        val inList = listFavorites.contains(selectedPokemon?.id)

        when {
            inList -> {
                listFavorites.remove(selectedPokemon?.id)
            }
            listFavorites.size >= MAX_FAVORITES -> {
                listFavorites.removeAt(MAX_FAVORITES - 1)
                selectedPokemon?.id?.let { idPokemon ->
                    listFavorites.add(idPokemon)
                }
            }
            else -> {
                selectedPokemon?.id?.let { idPokemon ->
                    listFavorites.add(idPokemon)
                }
            }
        }

        viewModel.onIntent(PokemonIntent.Reduce.SetListFavoritePokemon(listFavorites.toList()))
        preferences.myFavorites = listFavorites.joinToString(",")

    }
}