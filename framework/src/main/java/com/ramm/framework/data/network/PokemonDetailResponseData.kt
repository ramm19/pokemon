package com.ramm.framework.data.network

import com.google.gson.annotations.SerializedName
import com.ramm.core.domain.PokemonDetailUseCaseInfo
import com.ramm.core.domain.SpritesOtherHomeUseCaseInfo
import com.ramm.core.domain.SpritesOtherUseCaseInfo
import com.ramm.core.domain.SpritesUseCaseInfo
import com.ramm.core.domain.StatsStatUseCaseInfo
import com.ramm.core.domain.StatsUseCaseInfo
import com.ramm.core.domain.TypesTypeUseCaseInfo
import com.ramm.core.domain.TypesUseCaseInfo
import com.ramm.framework.data.mapper.DomainMapper

data class PokemonDetailResponseData(
    val height: Int,
    val weight: Int,
    val id: Int,
    val name: String,
    val sprites: SpritesResponseData,
    val types: List<TypesResponseData>,
    val stats: List<StatsResponseData>
) : DomainMapper<PokemonDetailUseCaseInfo> {
    override fun mapToDomainModel() = PokemonDetailUseCaseInfo(
        height = height,
        weight = weight,
        id = id,
        name = name,
        sprites = sprites.mapToDomainModel(),
        types = types.map { it.mapToDomainModel() },
        stats = stats.map { it.mapToDomainModel() }
    )
}

data class SpritesResponseData(
    val other: SpritesOtherResponseData
) : DomainMapper<SpritesUseCaseInfo> {
    override fun mapToDomainModel() = SpritesUseCaseInfo(other.mapToDomainModel())
}

data class SpritesOtherResponseData(
    val home: SpritesOtherHomeResponseData
) : DomainMapper<SpritesOtherUseCaseInfo> {
    override fun mapToDomainModel() = SpritesOtherUseCaseInfo(home.mapToDomainModel())
}

data class SpritesOtherHomeResponseData(
    @SerializedName("front_default")
    val frontDefault: String
) : DomainMapper<SpritesOtherHomeUseCaseInfo> {
    override fun mapToDomainModel() = SpritesOtherHomeUseCaseInfo(frontDefault)
}

data class TypesResponseData(
    val slot: Int,
    val type: TypesTypeResponseData
) : DomainMapper<TypesUseCaseInfo> {
    override fun mapToDomainModel() = TypesUseCaseInfo(
        slot,
        type.mapToDomainModel()
    )
}

data class TypesTypeResponseData(
    val name: String
) : DomainMapper<TypesTypeUseCaseInfo> {
    override fun mapToDomainModel() = TypesTypeUseCaseInfo(name)
}

data class StatsResponseData(
    val stat: StatsStatResponseData,
    @SerializedName("base_stat")
    val baseStat: Int
) : DomainMapper<StatsUseCaseInfo> {
    override fun mapToDomainModel() = StatsUseCaseInfo (
        stat.mapToDomainModel(),
        baseStat
    )
}

data class StatsStatResponseData(
    val name: String
) : DomainMapper<StatsStatUseCaseInfo> {
    override fun mapToDomainModel() = StatsStatUseCaseInfo(name)
}
