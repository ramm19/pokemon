package com.ramm.cuscatlanpokemon.ui.navigation

import androidx.navigation.navArgument

sealed class NavCommand(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList()
) {

    class ContentHome(feature: Feature) : NavCommand(feature)
    class ContentDetail(feature: Feature) : NavCommand(feature, "detail") {
        fun createRoute() = "${feature.route}/$subRoute"
    }
    class ContentProfile(feature: Feature) : NavCommand(feature, "profile") {
        fun createRoute() = "${feature.route}/$subRoute"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(feature.route)
            .plus(subRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

}