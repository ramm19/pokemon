package com.ramm.cuscatlanpokemon.ui.actions

import com.ramm.cuscatlanpokemon.preferences
import com.ramm.cuscatlanpokemon.ui.base.Action
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel

class SaveDataProfileAction(
    private val viewModel: PokemonViewModel
) : Action {
    override suspend fun execute() {
        preferences.nameProfile = viewModel.currentState.name
        preferences.hobby = viewModel.currentState.hobby
        preferences.birthDay = viewModel.currentState.birthDate
        preferences.document = viewModel.currentState.document
    }
}