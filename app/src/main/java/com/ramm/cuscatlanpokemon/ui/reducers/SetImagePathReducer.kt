package com.ramm.cuscatlanpokemon.ui.reducers

import com.ramm.cuscatlanpokemon.ui.base.Reducer
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

class SetImagePathReducer(
    private val imagePath: String
) : Reducer<PokemonViewState> {
    override suspend fun reduce(viewState: PokemonViewState) = viewState.copy(
        imagePath = imagePath
    )

}