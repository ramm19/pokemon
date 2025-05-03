package com.ramm.cuscatlanpokemon.ui.reducers

import com.ramm.cuscatlanpokemon.ui.base.Reducer
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

class SetListFavoritePokemonReducer(
    private val listFavoritePokemon: List<Int>
) : Reducer<PokemonViewState> {
    override suspend fun reduce(viewState: PokemonViewState) = viewState.copy(
        listFavoritePokemon = listFavoritePokemon
    )
}