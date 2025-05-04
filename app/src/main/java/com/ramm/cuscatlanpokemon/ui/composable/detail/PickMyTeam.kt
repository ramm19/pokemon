package com.ramm.cuscatlanpokemon.ui.composable.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramm.cuscatlanpokemon.R
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

@Composable
fun PickMyTeam(
    modifier: Modifier = Modifier,
    viewState: PokemonViewState,
    onIntent: (PokemonIntent) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Image(
            modifier = Modifier
                .clickable {
                    onIntent(PokemonIntent.Screen.EditFavoritePokemon)
                }
                .size(52.dp),
            painter = painterResource(
                if (viewState.listFavoritePokemon.contains(viewState.selectedPokemon?.entryNumber))
                    R.drawable.ic_pokemon_error
                else
                    R.drawable.ic_pokemon
            ),
            contentDescription = "myTeam"
        )
    }
}

@Preview
@Composable
fun PickMyTeamPreview() {
    val fakeViewState = PokemonViewState()
    PickMyTeam(Modifier, fakeViewState) {}
}
