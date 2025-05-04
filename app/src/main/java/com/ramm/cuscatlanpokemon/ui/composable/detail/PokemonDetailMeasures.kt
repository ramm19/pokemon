package com.ramm.cuscatlanpokemon.ui.composable.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramm.cuscatlanpokemon.R
import com.ramm.cuscatlanpokemon.theme.DarkNavyBlue
import com.ramm.cuscatlanpokemon.theme.SteelBlue

@Composable
fun PokemonDetailMeasure(
    modifier: Modifier = Modifier,
    height: String,
    weight: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        OneMeasureDetailPokemon(
            ImageVector.vectorResource(R.drawable.ic_weight),
            "Peso",
            weight,

        )

        OneMeasureDetailPokemon(
            ImageVector.vectorResource(R.drawable.ic_height),
            "Altura",
            height
        )
    }
}

@Composable
fun OneMeasureDetailPokemon(
    icon: ImageVector,
    measure: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(25.dp)
                .padding(end = 8.dp),
            imageVector = icon,
            contentDescription = null,
            tint = SteelBlue
        )

        Column {
            Text(
                text = value,
                color = DarkNavyBlue,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = measure,
                color = SteelBlue,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Composable
fun MeasureDetailPokemonPreview() {
    PokemonDetailMeasure(Modifier, "0.7 kg", "6.9 m")
}
