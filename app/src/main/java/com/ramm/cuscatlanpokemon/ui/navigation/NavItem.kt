package com.ramm.cuscatlanpokemon.ui.navigation

enum class NavItem(
    val navCommand: NavCommand,
    val title: String,
    val toolbar: ToolbarEnum
) {
    HOME(
        NavCommand.ContentHome(Feature.HOME),
        "Pokédex",
        ToolbarEnum.MAIN
    ),

    DETAIL(
        NavCommand.ContentDetail(Feature.HOME),
        "pok",
        ToolbarEnum.BACK
    ),

    PROFILE(
        NavCommand.ContentProfile(Feature.HOME),
        "Perfil",
        ToolbarEnum.BACK
    )

}


enum class ToolbarEnum {
    MAIN,
    BACK
}