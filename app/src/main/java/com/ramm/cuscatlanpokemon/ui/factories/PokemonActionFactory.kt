package com.ramm.cuscatlanpokemon.ui.factories

import com.ramm.core.usecases.base.GetAllPokemonFirstGenerationUseCase
import com.ramm.core.usecases.base.GetPokemonDetailUseCase
import com.ramm.cuscatlanpokemon.ui.actions.DoSearchPokemonAction
import com.ramm.cuscatlanpokemon.ui.actions.EditFavoritePokemonAction
import com.ramm.cuscatlanpokemon.ui.actions.GetAllPokemonFirstGenerationAction
import com.ramm.cuscatlanpokemon.ui.actions.GetDataProfileAction
import com.ramm.cuscatlanpokemon.ui.actions.GetDetailPokemonAction
import com.ramm.cuscatlanpokemon.ui.actions.SaveDataProfileAction
import com.ramm.cuscatlanpokemon.ui.actions.SaveImageProfileAction
import com.ramm.cuscatlanpokemon.ui.base.Action
import com.ramm.cuscatlanpokemon.ui.base.ActionFactory
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonCommand
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

class PokemonActionFactory(
    private val getAllPokemonFirstGenerationUseCase: GetAllPokemonFirstGenerationUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ActionFactory<PokemonViewState, PokemonCommand, PokemonIntent, PokemonIntent.Screen, PokemonViewModel> {
    override fun fromIntent(
        intent: PokemonIntent.Screen,
        viewModel: PokemonViewModel
    ): Action {
        return when (intent) {
            PokemonIntent.Screen.DoSearchPokemon -> DoSearchPokemonAction(viewModel)
            PokemonIntent.Screen.EditFavoritePokemon -> EditFavoritePokemonAction(viewModel)
            PokemonIntent.Screen.GetAllPokemonFirstGeneration -> GetAllPokemonFirstGenerationAction(
                viewModel,
                getAllPokemonFirstGenerationUseCase
            )
            PokemonIntent.Screen.GetDataProfile -> GetDataProfileAction(viewModel)
            is PokemonIntent.Screen.GetDetailPokemon -> GetDetailPokemonAction(
                viewModel = viewModel,
                getPokemonDetailUseCase = getPokemonDetailUseCase,
                idPokemon = intent.idPokemon
            )
            PokemonIntent.Screen.SaveDataProfile -> SaveDataProfileAction(viewModel)
            PokemonIntent.Screen.SaveImageProfile -> SaveImageProfileAction(viewModel)
        }
    }
}