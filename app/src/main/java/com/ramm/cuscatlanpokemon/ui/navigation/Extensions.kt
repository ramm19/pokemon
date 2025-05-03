package com.ramm.cuscatlanpokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}

inline fun <reified T> getKoinInstance(): T {
    return object: KoinComponent {
        val value: T by inject()
    }.value
}