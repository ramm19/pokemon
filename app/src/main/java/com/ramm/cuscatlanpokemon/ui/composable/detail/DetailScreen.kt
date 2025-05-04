package com.ramm.cuscatlanpokemon.ui.composable.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramm.core.domain.PokemonDetailUseCaseInfo
import com.ramm.cuscatlanpokemon.theme.BrightRedOrange
import com.ramm.cuscatlanpokemon.theme.DarkGray
import com.ramm.cuscatlanpokemon.theme.DarkNavyBlue
import com.ramm.cuscatlanpokemon.theme.VeryLightGrey
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

@Composable
fun DetailScreen(
    viewState: PokemonViewState,
    onIntent: (PokemonIntent) -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = VeryLightGrey)
            .verticalScroll(rememberScrollState())
    ) {
        BackgroundComposable(
            topContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImageTypePokemon(
                        viewState.detailPokemon?.sprites?.other?.home?.frontDefault ?: "",
                        viewState.detailPokemon?.types ?: emptyList()
                    )
                }
            },
            pickMyTeamContent = {
                PickMyTeam(
                    modifier = Modifier
                        .padding(top = 8.dp, end = 32.dp),
                    viewState = viewState,
                    onIntent = onIntent
                )
            }
        )

        PokemonDetailMeasure(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 12.dp),
            height = "${(viewState.detailPokemon?.height ?: 0).toDouble() / 10.0} m",
            weight = "${(viewState.detailPokemon?.weight ?: 0).toDouble() / 10.0} kg"
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp),
            text = "Este Pokémon tiene plantado un bulbo en el lomo desde que nace. Esta semilla crece y se desarrolla a lo largo del ciclo de vida de Bulbasaur a medida que suceden sus evoluciones. El bulbo absorbe y almacena la energía solar que Bulbasaur necesita para crecer.",
            color = DarkGray,
            style = MaterialTheme.typography.labelMedium
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp),
            text = "Estadísticas",
            color = DarkNavyBlue,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold
        )

        PokemonDetailStats(
            modifier = Modifier
                .padding(start = 32.dp, end = 32.dp, bottom = 32.dp),
            stats = viewState.detailPokemon?.stats ?: emptyList()
        )
    }
}

@Preview
@Composable
fun DetailScreenPreview(){
    val fakeViewState = PokemonViewState()
    DetailScreen(fakeViewState){}
}
