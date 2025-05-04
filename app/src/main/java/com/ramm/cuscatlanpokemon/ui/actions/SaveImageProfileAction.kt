package com.ramm.cuscatlanpokemon.ui.actions

import com.ramm.cuscatlanpokemon.preferences
import com.ramm.cuscatlanpokemon.ui.base.Action
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel

class SaveImageProfileAction(
    private val viewModel: PokemonViewModel
) : Action {
    override suspend fun execute() {
        preferences.pathFile = viewModel.currentState.imagePath
    }
}