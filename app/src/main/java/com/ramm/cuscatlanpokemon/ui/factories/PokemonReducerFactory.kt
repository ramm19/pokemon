package com.ramm.cuscatlanpokemon.ui.factories

import com.ramm.cuscatlanpokemon.ui.base.Reducer
import com.ramm.cuscatlanpokemon.ui.base.ReducerFactory
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.reducers.SetBirthDateReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetDetailPokemonReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetDocumentReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetHobbyReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetImagePathReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetListFavoritePokemonReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetListPokemonFilterReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetListPokemonReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetNameReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetSearchReducer
import com.ramm.cuscatlanpokemon.ui.reducers.SetSelectedPokemonReducer
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

class PokemonReducerFactory : ReducerFactory<PokemonIntent.Reduce, PokemonViewState> {
    override fun fromIntent(intent: PokemonIntent.Reduce): Reducer<PokemonViewState> {
        return when (intent) {
            is PokemonIntent.Reduce.SetBirthDate -> SetBirthDateReducer(intent.birthDate)
            is PokemonIntent.Reduce.SetDetailPokemon -> SetDetailPokemonReducer(intent.detailPokemon)
            is PokemonIntent.Reduce.SetDocument -> SetDocumentReducer(intent.document)
            is PokemonIntent.Reduce.SetHobby -> SetHobbyReducer(intent.hobby)
            is PokemonIntent.Reduce.SetImagePath -> SetImagePathReducer(intent.imagePath)
            is PokemonIntent.Reduce.SetListFavoritePokemon -> SetListFavoritePokemonReducer(intent.listFavoritePokemon)
            is PokemonIntent.Reduce.SetListPokemon -> SetListPokemonReducer(intent.listPokemon)
            is PokemonIntent.Reduce.SetListPokemonFilter -> SetListPokemonFilterReducer(intent.listPokemonFilter)
            is PokemonIntent.Reduce.SetName -> SetNameReducer(intent.name)
            is PokemonIntent.Reduce.SetSearch -> SetSearchReducer(intent.search)
            is PokemonIntent.Reduce.SetSelectedPokemon -> SetSelectedPokemonReducer(intent.selectedPokemon)
        }
    }
}