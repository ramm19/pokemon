package com.ramm.cuscatlanpokemon.ui.composable.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ramm.core.domain.PokemonEntriesUseCaseInfo
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent

@Composable
fun PokemonCard(
    pokemon: PokemonEntriesUseCaseInfo,
    onIntent: (PokemonIntent) -> Unit,
    goToDetail: () -> Unit
) {
    BoxWithConstraints (
        modifier = Modifier
            .aspectRatio(1f)
            .background(LightGray, RoundedCornerShape(12.dp))
            .clickable {
                onIntent(PokemonIntent.Reduce.SetSelectedPokemon(pokemon))
                onIntent(PokemonIntent.Screen.GetDetailPokemon(pokemon.entryNumber))
                goToDetail()
            }
            .padding(8.dp)
    ) {
        val boxWidth = maxWidth

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "#${pokemon.entryNumber}",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .align(Alignment.End)
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = rememberAsyncImagePainter(pokemon.urlImage),
                contentDescription = pokemon.pokemonSpecies.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(boxWidth * 0.6f)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = pokemon.pokemonSpecies.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}
