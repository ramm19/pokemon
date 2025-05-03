package com.ramm.cuscatlanpokemon.ui.actions

import com.ramm.cuscatlanpokemon.preferences
import com.ramm.cuscatlanpokemon.ui.base.Action
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel

class GetDataProfileAction(
    private val viewmodel: PokemonViewModel,
) : Action {
    override suspend fun execute() {
        viewmodel.onIntent(PokemonIntent.Reduce.SetName(preferences.nameProfile))
        viewmodel.onIntent(PokemonIntent.Reduce.SetHobby(preferences.hobby))
        viewmodel.onIntent(PokemonIntent.Reduce.SetBirthDate(preferences.birthDay))
        viewmodel.onIntent(PokemonIntent.Reduce.SetDocument(preferences.document))
        viewmodel.onIntent(PokemonIntent.Reduce.SetImagePath(preferences.pathFile))

        val favorites = preferences.myFavorites
        val listFavorites : List<Int> = if (favorites.isBlank())
            emptyList()
        else
            favorites.split(",").map { it.toInt() }
        viewmodel.onIntent(PokemonIntent.Reduce.SetListFavoritePokemon(listFavorites))
    }
}