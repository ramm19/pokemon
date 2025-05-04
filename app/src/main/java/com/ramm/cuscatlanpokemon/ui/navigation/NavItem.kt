package com.ramm.cuscatlanpokemon.ui.navigation

enum class NavItem(
    val navCommand: NavCommand,
    val title: String
) {
    HOME(
        NavCommand.ContentHome(Feature.HOME),
        "Pokédex"
    ),

    DETAIL(
        NavCommand.ContentDetail(Feature.HOME),
        "pok"
    ),

    PROFILE(
        NavCommand.ContentProfile(Feature.HOME),
        "Perfil"
    )

}
