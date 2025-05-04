package com.ramm.cuscatlanpokemon.ui.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ramm.cuscatlanpokemon.ui.base.MVIViewModel
import com.ramm.cuscatlanpokemon.ui.factories.PokemonActionFactory
import com.ramm.cuscatlanpokemon.ui.factories.PokemonReducerFactory
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonCommand
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val pokemonReducerFactory: PokemonReducerFactory,
    private val pokemonActionFactory: PokemonActionFactory
) : MVIViewModel<PokemonViewState, PokemonCommand, PokemonIntent>() {

    init {
        initState(PokemonViewState())
        onIntent(PokemonIntent.Screen.GetDataProfile)
    }


    override suspend fun handleIntent(intent: PokemonIntent) {
        viewModelScope.launch {
            when (intent) {
                is PokemonIntent.Screen -> pokemonActionFactory
                    .fromIntent(intent, this@PokemonViewModel)
                    .execute()
                is PokemonIntent.Reduce -> pokemonReducerFactory
                    .fromIntent(intent)
                    .reduce(currentState)
                    .let { setState(it) }
            }
        }
    }
}