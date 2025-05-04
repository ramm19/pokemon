package com.ramm.cuscatlanpokemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramm.cuscatlanpokemon.ui.BackTopAppBar
import com.ramm.cuscatlanpokemon.ui.MainTopAppBar
import com.ramm.cuscatlanpokemon.ui.PokemonAppState
import com.ramm.cuscatlanpokemon.ui.ToolbarEnum
import com.ramm.cuscatlanpokemon.ui.navigation.NavItem
import com.ramm.cuscatlanpokemon.ui.navigation.Navigation
import com.ramm.cuscatlanpokemon.ui.rememberPokemonAppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonApp(
    appState: PokemonAppState = rememberPokemonAppState(NavItem.HOME)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )

    Scaffold(
        topBar = {
            when(appState.typeToolbar){
                ToolbarEnum.MAIN -> {
                    MainTopAppBar(
                        onProfileClick = appState.goToProfile
                    )
                }
                ToolbarEnum.BACK -> {
                    BackTopAppBar(
                        title = appState.toolbarTitle,
                        onBackClick = { appState.onUpClick() },
                        idPokemon = appState.idPokemon,
                        scrollBehavior = scrollBehavior
                    )
                }
                ToolbarEnum.NONE -> {}
            }


        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            Navigation(appState = appState)
        }
    }

}
