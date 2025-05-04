package com.ramm.cuscatlanpokemon.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var typeToolbar by mutableStateOf(ToolbarEnum.MAIN)
        private set

    var toolbarTitle by mutableStateOf("")
        private set

    var idPokemon by mutableStateOf("")
        private set

    fun updateToolbar(toolbar: ToolbarEnum) {
        typeToolbar = toolbar
    }

    fun updateToolbarTitle(title: String) {
        toolbarTitle = title
    }

    fun updateIdPokemon(id: String) {
        idPokemon = id
    }

    fun onUpClick() {
        navController.popBackStack()
    }

    var goToProfile: () -> Unit by mutableStateOf({})
}

enum class ToolbarEnum {
    MAIN,
    BACK,
    NONE
}