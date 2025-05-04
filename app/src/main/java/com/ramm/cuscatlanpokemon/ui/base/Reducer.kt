package com.ramm.cuscatlanpokemon.ui.base

interface Reducer<VIEWSTATE> {
    suspend fun reduce(viewState: VIEWSTATE): VIEWSTATE
}