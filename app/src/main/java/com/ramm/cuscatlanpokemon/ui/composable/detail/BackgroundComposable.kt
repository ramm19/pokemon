package com.ramm.cuscatlanpokemon.ui.composable.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.ramm.cuscatlanpokemon.theme.EmeraldGreen
import com.ramm.cuscatlanpokemon.theme.MintGreen

@Composable
fun BackgroundComposable(
    modifier: Modifier = Modifier,
    topContent: @Composable () -> Unit,
    backgroundColor: Color = MintGreen
) {
    Box (modifier = modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .heightIn(min = 0.dp)
                .align(Alignment.BottomCenter)
                .height(LocalConfiguration.current.screenHeightDp.dp / 4)
                .clip(RoundedCornerShape(32.dp))
                .background(backgroundColor)
        )

        topContent()
    }
}