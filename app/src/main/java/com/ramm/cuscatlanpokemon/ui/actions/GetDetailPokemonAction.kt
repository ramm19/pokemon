package com.ramm.cuscatlanpokemon.ui.actions

import com.ramm.core.domain.Failure
import com.ramm.core.domain.Success
import com.ramm.core.usecases.base.GetPokemonDetailUseCase
import com.ramm.cuscatlanpokemon.ui.base.Action
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel

class GetDetailPokemonAction(
    private val viewModel: PokemonViewModel,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val idPokemon: Int
) : Action {
    override suspend fun execute() {
        when (val result = getPokemonDetailUseCase(idPokemon)) {
            is Success -> {
                viewModel.onIntent(PokemonIntent.Reduce.SetDetailPokemon(result.data))
            }

            is Failure -> {
            //todo mostrar error
            }
        }
    }
}