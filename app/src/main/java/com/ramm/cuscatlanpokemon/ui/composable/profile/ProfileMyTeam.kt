package com.ramm.cuscatlanpokemon.ui.composable.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramm.cuscatlanpokemon.theme.MintGreen
import com.ramm.cuscatlanpokemon.theme.RoyalBlue
import com.ramm.cuscatlanpokemon.ui.composable.home.PokemonCard
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState

@Composable
fun ProfileMyTeam(
    modifier: Modifier = Modifier,
    viewState: PokemonViewState,
    onIntent: (PokemonIntent) -> Unit,
    goToDetail: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = {
        viewState.listFavoritePokemonData.size
    })

    HorizontalPager(
        modifier = modifier,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSize = PageSize.Fixed(250.dp),
        state = pagerState
    ) { page ->
        PokemonCard(
            pokemon = viewState.listFavoritePokemonData[page],
            onIntent = onIntent,
            goToDetail = { goToDetail() }
        )
    }

}
