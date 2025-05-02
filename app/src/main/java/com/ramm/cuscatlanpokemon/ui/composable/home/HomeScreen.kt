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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramm.core.domain.PokemonUseCaseInfo
import com.ramm.cuscatlanpokemon.theme.CharcoalGray
import com.ramm.cuscatlanpokemon.theme.DeepPetroleumBlue
import com.ramm.cuscatlanpokemon.theme.GoldenYellow
import com.ramm.cuscatlanpokemon.theme.MediumGray
import com.ramm.cuscatlanpokemon.theme.Transparent
import com.ramm.cuscatlanpokemon.theme.VeryLightGrey

@Composable
fun HomeScreen() {

    var search by remember { mutableStateOf("") }
    val listPokemon = remember { mutableStateOf(listOf<PokemonUseCaseInfo>()) }
    val listPokemonFilter = remember(search) {
        if (search.isBlank()) {
            listPokemon.value
        } else {
            listPokemon.value.filter {
                it.id.contains(search, ignoreCase = true) ||
                it.name.contains(search, ignoreCase = true)
            }
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
            value = search,
            textStyle = MaterialTheme.typography.labelLarge,
            onValueChange = {
                search = it
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
            listPokemonFilter.isEmpty() -> {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    text = "No hay datos.",
                    style = MaterialTheme.typography.titleLarge,
                    color = MediumGray,
                    fontWeight = FontWeight.Bold
                )
            }
            search.isNotBlank() -> {
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
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listPokemonFilter) { pokemon ->
                PokemonCard(pokemon = pokemon)
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
