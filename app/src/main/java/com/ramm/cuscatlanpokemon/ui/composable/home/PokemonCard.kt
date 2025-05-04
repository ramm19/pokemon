package com.ramm.cuscatlanpokemon.ui.composable.home

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.ramm.core.domain.PokemonEntriesUseCaseInfo
import com.ramm.cuscatlanpokemon.R
import com.ramm.cuscatlanpokemon.theme.DarkNavyBlue
import com.ramm.cuscatlanpokemon.theme.MediumGray
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.navigation.formatIdPokemon

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun PokemonCard(
    pokemon: PokemonEntriesUseCaseInfo,
    onIntent: (PokemonIntent) -> Unit,
    goToDetail: () -> Unit
) {

    LaunchedEffect(true) {
        Log.d("ramm", "este es el image ${pokemon.urlImage}")
    }

    BoxWithConstraints (
        modifier = Modifier
            .aspectRatio(1f)
            .background( Color.White, RoundedCornerShape(12.dp))
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
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.End),
                text = formatIdPokemon(pokemon.entryNumber),
                style = MaterialTheme.typography.labelMedium,
                color = MediumGray
            )

            AsyncImage(
                modifier = Modifier
                    .size(boxWidth * 0.65f)
                    .align(Alignment.CenterHorizontally),
                model = pokemon.urlImage,
                contentDescription = pokemon.pokemonSpecies.name,
                contentScale = ContentScale.Fit,
                placeholder = painterResource(R.drawable.ic_pokemon),
                error = painterResource(R.drawable.ic_pokemon_error)
            )

            Text(
                modifier = Modifier
                    .align(Alignment.Start),
                text = pokemon.pokemonSpecies.name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleMedium,
                color = DarkNavyBlue,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
