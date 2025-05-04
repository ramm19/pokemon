package com.ramm.cuscatlanpokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
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

fun formatIdPokemon(id: Int): String = String.format("#%03d", id)

fun getDateFormatter(date: String) : LocalDate? {
    val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
    return try {
        LocalDate.parse(date, formatter)
    } catch (e: DateTimeParseException) {
        null
    }
}
