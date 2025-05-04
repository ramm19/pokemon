package com.ramm.cuscatlanpokemon.ui.actions

import com.ramm.cuscatlanpokemon.ui.base.Action
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.navigation.formatIdPokemon
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel

class DoSearchPokemonAction(
    private val viewModel: PokemonViewModel
) : Action {
    override suspend fun execute() {
        val listPokemonFilter = if (viewModel.currentState.search.isBlank()) {
            viewModel.currentState.listPokemon
        } else {
            viewModel.currentState.listPokemon.filter {
                formatIdPokemon(it.entryNumber).contains(viewModel.currentState.search, ignoreCase = true) ||
                it.pokemonSpecies.name.contains(viewModel.currentState.search, ignoreCase = true)
            }
        }

        viewModel.onIntent(PokemonIntent.Reduce.SetListPokemonFilter(listPokemonFilter))
    }
}