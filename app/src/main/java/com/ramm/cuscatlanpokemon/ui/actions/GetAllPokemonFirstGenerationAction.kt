package com.ramm.cuscatlanpokemon.ui.actions

import com.ramm.core.domain.Failure
import com.ramm.core.domain.Success
import com.ramm.core.usecases.base.GetAllPokemonFirstGenerationUseCase
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
                viewModel.onIntent(PokemonIntent.Reduce.SetListPokemon(result.data.pokemonEntries))
                viewModel.onIntent(PokemonIntent.Reduce.SetListPokemonFilter(result.data.pokemonEntries))
            }
        }
    }
}