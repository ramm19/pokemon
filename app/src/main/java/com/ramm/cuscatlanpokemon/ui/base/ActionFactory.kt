package com.ramm.cuscatlanpokemon.ui.base

interface ActionFactory<VIEWSTATE, VIEWCOMMAND, VIEWINTENT, SCREENINTENT: VIEWINTENT,
        VIEWMODEL: MVIViewModel<VIEWSTATE, VIEWCOMMAND, VIEWINTENT>> {

    fun fromIntent(intent: SCREENINTENT, viewModel: VIEWMODEL): Action
}