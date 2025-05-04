package com.ramm.cuscatlanpokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.ramm.cuscatlanpokemon.ui.PokemonAppState

@Composable
fun Navigation(
    appState: PokemonAppState
) {
    NavHost(
        navController = appState.navController,
        startDestination = Feature.HOME.route
    ) {
        homeGraph(appState = appState)
    }
}
