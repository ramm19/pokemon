package com.ramm.cuscatlanpokemon.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramm.cuscatlanpokemon.ui.navigation.NavItem

@Composable
fun rememberPokemonAppState(
    initNavItem: NavItem,
    navController: NavHostController = rememberNavController()
) : PokemonAppState = remember(initNavItem, navController) {
    PokemonAppState(initNavItem, navController)
}

class PokemonAppState(
    initNavItem: NavItem,
    val navController: NavHostController
) {
    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    private var _toolbarTitle: String = ""

    fun setToolbarTitle(title: String) {
        _toolbarTitle = title
    }

    fun getToolbarTitle() = _toolbarTitle

    fun onUpClick() {
        navController.popBackStack()
    }
}