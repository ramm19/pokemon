package com.ramm.cuscatlanpokemon.di

import com.ramm.cuscatlanpokemon.ui.factories.PokemonActionFactory
import com.ramm.cuscatlanpokemon.ui.factories.PokemonReducerFactory
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single { PokemonActionFactory(get(), get()) }
    single { PokemonReducerFactory() }

    viewModel { PokemonViewModel(get(), get()) }
}