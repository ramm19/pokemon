package com.ramm.cuscatlanpokemon.ui.composable.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ramm.core.domain.StatsStatUseCaseInfo
import com.ramm.core.domain.StatsUseCaseInfo
import com.ramm.cuscatlanpokemon.theme.CharcoalGray
import com.ramm.cuscatlanpokemon.theme.EmeraldGreen
import com.ramm.cuscatlanpokemon.theme.MintGreen
import com.ramm.cuscatlanpokemon.theme.NearBlack
import com.ramm.cuscatlanpokemon.utils.MAX_STATS_ATTACK
import com.ramm.cuscatlanpokemon.utils.MAX_STATS_DEFENSE
import com.ramm.cuscatlanpokemon.utils.MAX_STATS_HP
import com.ramm.cuscatlanpokemon.utils.MAX_STATS_SPECIAL_ATTACK
import com.ramm.cuscatlanpokemon.utils.MAX_STATS_SPECIAL_DEFENSE
import com.ramm.cuscatlanpokemon.utils.MAX_STATS_SPEED

@Composable
fun PokemonDetailStats(
    modifier: Modifier = Modifier,
    stats: List<StatsUseCaseInfo>
) {
    val statMap = stats.associateBy { it.stat.name }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        PokemonStatsEnum.values().forEach { type ->
            val progress = statMap[type.nameStat]?.baseStat ?: 0

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier
                        .weight(0.35f),
                    text = type.labelStat,
                    style = MaterialTheme.typography.labelLarge,
                    color = CharcoalGray
                )

                StatProgressBar(
                    modifier = Modifier
                        .weight(0.55f),
                    value = progress,
                    maxValue = type.max
                )

                Text(
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(start = 4.dp),
                    text = progress.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = NearBlack
                )
            }
        }
    }
}

@Composable
fun StatProgressBar(
    modifier: Modifier = Modifier,
    value: Int,
    maxValue: Int,
    backgroundColor: Color = MintGreen,
    progressColor: Color = EmeraldGreen,
    height: Dp = 12.dp,
    cornerRadius: Dp = 6.dp
) {
    val progressRatio = (value.toFloat() / maxValue).coerceIn(0f, 1f)

    Box (
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(cornerRadius))
            .background(backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progressRatio)
                .clip(RoundedCornerShape(cornerRadius))
                .background(progressColor)
        )
    }
}

enum class PokemonStatsEnum(val nameStat: String, val labelStat: String, val max: Int) {
    HP("hp", "HP", MAX_STATS_HP),
    ATTACK("attack", "Ataque", MAX_STATS_ATTACK),
    DEFENSE("defense", "Defensa", MAX_STATS_DEFENSE),
    SPECIAL_ATTACK("special-attack", "Ataque especial", MAX_STATS_SPECIAL_ATTACK),
    SPECIAL_DEFENSE("especial-defense", "Defensa especial", MAX_STATS_SPECIAL_DEFENSE),
    SPEED("speed", "Velocidad", MAX_STATS_SPEED)
}

@Preview
@Composable
fun PokemonDetailStatsPreview() {
    PokemonDetailStats(
        Modifier,
        listOf(
            StatsUseCaseInfo(
                stat = StatsStatUseCaseInfo("hp"),
                45
            ),
            StatsUseCaseInfo(
                stat = StatsStatUseCaseInfo("attack"),
                49
            ),
            StatsUseCaseInfo(
                stat = StatsStatUseCaseInfo("defense"),
                49
            ),
            StatsUseCaseInfo(
                stat = StatsStatUseCaseInfo("special-attack"),
                65
            ),
            StatsUseCaseInfo(
                stat = StatsStatUseCaseInfo("especial-defense"),
                65
            ),
            StatsUseCaseInfo(
                stat = StatsStatUseCaseInfo("speed"),
                45
            ),
        )
    )
}
