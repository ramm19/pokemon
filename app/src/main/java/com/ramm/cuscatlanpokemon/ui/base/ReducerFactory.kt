package com.ramm.cuscatlanpokemon.ui.base

interface ReducerFactory<REDUCERINTENT, VIEWSTATE> {
    fun fromIntent(intent: REDUCERINTENT): Reducer<VIEWSTATE>
}