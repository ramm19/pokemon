package com.ramm.framework.di

import com.ramm.core.repositories.PokemonRepository
import com.ramm.framework.data.repositories.PokemonRepositoryImpl
import com.ramm.framework.utils.Connectivity
import com.ramm.framework.utils.ConnectivityImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
    factory<PokemonRepository> { PokemonRepositoryImpl(get()) }
}