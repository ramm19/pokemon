package com.ramm.core.di

import com.ramm.core.usecases.GetAllPokemonFirstGenerationUseCaseImpl
import com.ramm.core.usecases.GetPokemonDetailUseCaseImpl
import com.ramm.core.usecases.base.GetAllPokemonFirstGenerationUseCase
import com.ramm.core.usecases.base.GetPokemonDetailUseCase
import org.koin.dsl.module

val interactionModule = module {
    factory<GetAllPokemonFirstGenerationUseCase> { GetAllPokemonFirstGenerationUseCaseImpl(get()) }
    factory<GetPokemonDetailUseCase> { GetPokemonDetailUseCaseImpl(get()) }
}
