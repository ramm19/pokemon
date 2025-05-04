package com.ramm.core.domain

data class PokemonDetailUseCaseInfo(
    val height: Int,
    val weight: Int,
    val id: Int,
    val name: String,
    val sprites: SpritesUseCaseInfo,
    val types: List<TypesUseCaseInfo>,
    val stats: List<StatsUseCaseInfo>
)

data class SpritesUseCaseInfo(
    val other: SpritesOtherUseCaseInfo
)

data class SpritesOtherUseCaseInfo(
    val home: SpritesOtherHomeUseCaseInfo
)

data class SpritesOtherHomeUseCaseInfo(
    val frontDefault: String
)

data class TypesUseCaseInfo(
    val slot: Int,
    val type: TypesTypeUseCaseInfo
)

data class TypesTypeUseCaseInfo(
    val name: String
)

data class StatsUseCaseInfo(
    val stat: StatsStatUseCaseInfo,
    val baseStat: Int
)

data class StatsStatUseCaseInfo(
    val name: String
)
