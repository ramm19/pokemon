package com.ramm.cuscatlanpokemon.ui.reducers

import com.ramm.core.domain.PokemonEntriesUseCaseInfo
import com.ramm.cuscatlanpokemon.ui.base.Reducer
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

class SetListFavoritePokemonReducer(
    private val listFavoritePokemon: List<Int>,
    private val listFavoritePokemonData: List<PokemonEntriesUseCaseInfo>
) : Reducer<PokemonViewState> {
    override suspend fun reduce(viewState: PokemonViewState) = viewState.copy(
        listFavoritePokemon = listFavoritePokemon,
        listFavoritePokemonData = listFavoritePokemonData
    )
}