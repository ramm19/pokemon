package com.ramm.cuscatlanpokemon.ui.actions

import com.ramm.cuscatlanpokemon.preferences
import com.ramm.cuscatlanpokemon.ui.base.Action
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel

class GetDataProfileAction(
    private val viewModel: PokemonViewModel,
) : Action {
    override suspend fun execute() {
        viewModel.onIntent(PokemonIntent.Reduce.SetName(preferences.nameProfile))
        viewModel.onIntent(PokemonIntent.Reduce.SetHobby(preferences.hobby))
        viewModel.onIntent(PokemonIntent.Reduce.SetBirthDate(preferences.birthDay))
        viewModel.onIntent(PokemonIntent.Reduce.SetDocument(preferences.document))
        viewModel.onIntent(PokemonIntent.Reduce.SetImagePath(preferences.pathFile))
    }
}