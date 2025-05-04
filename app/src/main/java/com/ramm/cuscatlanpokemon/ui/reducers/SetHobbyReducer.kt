package com.ramm.cuscatlanpokemon.ui.reducers

import com.ramm.cuscatlanpokemon.ui.base.Reducer
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

class SetHobbyReducer(
    private val hobby: String
) : Reducer<PokemonViewState> {
    override suspend fun reduce(viewState: PokemonViewState) = viewState.copy(
        hobby = hobby
    )
}