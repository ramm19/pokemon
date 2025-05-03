package com.ramm.cuscatlanpokemon.ui.reducers

import com.ramm.core.domain.PokemonDetailUseCaseInfo
import com.ramm.cuscatlanpokemon.ui.base.Reducer
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

class SetDetailPokemonReducer(
    private val detailPokemon: PokemonDetailUseCaseInfo?
) : Reducer<PokemonViewState> {
    override suspend fun reduce(viewState: PokemonViewState) = viewState.copy(
        detailPokemon = detailPokemon
    )
}