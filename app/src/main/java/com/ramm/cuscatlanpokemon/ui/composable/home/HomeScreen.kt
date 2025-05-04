package com.ramm.cuscatlanpokemon.ui.composable.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramm.cuscatlanpokemon.preferences
import com.ramm.cuscatlanpokemon.theme.CharcoalGray
import com.ramm.cuscatlanpokemon.theme.DeepPetroleumBlue
import com.ramm.cuscatlanpokemon.theme.GoldenYellow
import com.ramm.cuscatlanpokemon.theme.MediumGray
import com.ramm.cuscatlanpokemon.theme.Transparent
import com.ramm.cuscatlanpokemon.theme.VeryLightGrey
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

@Composable
fun HomeScreen(
    viewState: PokemonViewState,
    onIntent: (PokemonIntent) -> Unit,
    goToProfile: () -> Unit,
    goToDetail: () -> Unit
) {

    LaunchedEffect(true) {

        if (preferences.nameProfile.isBlank()) {
            goToProfile()
        } else {
            onIntent(PokemonIntent.Screen.GetAllPokemonFirstGeneration)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VeryLightGrey)
    ) {
        Text(
            modifier = Modifier
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            text = buildAnnotatedString {
                append("¡Hola, ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.ExtraBold
                    )
                ) {
                    append("bienvenido!")
                }
            },
            color = DeepPetroleumBlue,
            style = MaterialTheme.typography.titleLarge
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .border(width = 1.dp, color = MediumGray, shape = MaterialTheme.shapes.extraLarge),
            value = viewState.search,
            textStyle = MaterialTheme.typography.labelLarge,
            onValueChange = { newText ->
                onIntent(PokemonIntent.Reduce.SetSearch(newText))
                onIntent(PokemonIntent.Screen.DoSearchPokemon)
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = GoldenYellow,
                            shape = CircleShape
                        ),
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            placeholder = {
                Text(
                    text = "Buscar",
                    color = CharcoalGray,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Transparent,
                unfocusedContainerColor = Transparent,
                disabledIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                focusedIndicatorColor = Transparent
            )
        )

        when {
            viewState.listPokemonFilter.isEmpty() -> {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    text = "No hay datos.",
                    style = MaterialTheme.typography.titleLarge,
                    color = MediumGray,
                    fontWeight = FontWeight.Bold
                )
            }
            viewState.search.isNotBlank() -> {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    text = "Resultado de búsqueda",
                    style = MaterialTheme.typography.titleLarge,
                    color = MediumGray,
                    fontWeight = FontWeight.Bold
                )
            }
            else -> {}
        }

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(viewState.listPokemonFilter, key = { it.entryNumber }) { pokemon ->
                PokemonCard(
                    pokemon = pokemon,
                    onIntent,
                    goToDetail
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val fakeViewState = PokemonViewState()
    HomeScreen(fakeViewState, {}, {}){}
}
