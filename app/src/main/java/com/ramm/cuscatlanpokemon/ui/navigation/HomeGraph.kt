package com.ramm.cuscatlanpokemon.ui.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.ramm.cuscatlanpokemon.ui.PokemonAppState
import com.ramm.cuscatlanpokemon.ui.ToolbarEnum
import com.ramm.cuscatlanpokemon.ui.composable.detail.DetailScreen
import com.ramm.cuscatlanpokemon.ui.composable.home.HomeScreen
import com.ramm.cuscatlanpokemon.ui.composable.profile.ProfileScreen
import com.ramm.cuscatlanpokemon.ui.viewmodels.PokemonViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeGraph (
    appState: PokemonAppState
) {
    navigation(
        route = Feature.HOME.route,
        startDestination = NavCommand.ContentHome(Feature.HOME).route
    ) {

        composable(NavCommand.ContentHome(Feature.HOME)) { entry ->
            val pokemonViewModel = koinViewModel<PokemonViewModel>(
                viewModelStoreOwner = remember(entry) {
                    appState.navController.getBackStackEntry(Feature.HOME.route)
                }
            )
            val viewState by pokemonViewModel.viewState.collectAsState()
            Log.d("ramm", "Collecting viewState: $viewState")

            appState.updateToolbar(ToolbarEnum.MAIN)
            appState.goToProfile = {
                appState.navController.navigate(
                    NavCommand.ContentProfile(Feature.HOME).route
                )
            }
            HomeScreen(
                viewState = viewState,
                onIntent = { intent -> pokemonViewModel.onIntent(intent) },
                goToProfile = {
                    appState.navController.navigate(
                        NavCommand.ContentProfile(Feature.HOME).route
                    )
                },
                goToDetail = {
                    appState.navController.navigate(
                        NavCommand.ContentDetail(Feature.HOME).route
                    )
                }
            )
        }

        composable(NavCommand.ContentDetail(Feature.HOME)) { entry ->
            val pokemonViewModel = koinViewModel<PokemonViewModel>(
                viewModelStoreOwner = remember(entry) {
                    appState.navController.getBackStackEntry(Feature.HOME.route)
                }
            )
            val viewState by pokemonViewModel.viewState.collectAsState()

            appState.updateToolbar(ToolbarEnum.BACK)
            appState.updateToolbarTitle(viewState.selectedPokemon?.pokemonSpecies?.name ?: "")
            appState.updateIdPokemon(formatIdPokemon(viewState.selectedPokemon?.entryNumber ?: 0))
            DetailScreen(
                viewState = viewState,
                onIntent = { intent -> pokemonViewModel.onIntent(intent) }
            )
        }

        composable(NavCommand.ContentProfile(Feature.HOME)) { entry ->
            val pokemonViewModel = koinViewModel<PokemonViewModel>(
                viewModelStoreOwner = remember(entry) {
                    appState.navController.getBackStackEntry(Feature.HOME.route)
                }
            )
            val viewState by pokemonViewModel.viewState.collectAsState()
            appState.updateToolbar(ToolbarEnum.NONE)
            ProfileScreen(
                viewState = viewState,
                onIntent = { intent -> pokemonViewModel.onIntent(intent) },
                goBack = { appState.onUpClick() },
                goToDetail = {
                    appState.navController.navigate(
                        NavCommand.ContentDetail(Feature.HOME).route
                    )
                }
            )
        }
    }
}