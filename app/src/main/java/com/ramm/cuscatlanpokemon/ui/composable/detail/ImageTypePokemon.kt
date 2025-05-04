package com.ramm.cuscatlanpokemon.ui.composable.detail

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ramm.core.domain.TypesTypeUseCaseInfo
import com.ramm.core.domain.TypesUseCaseInfo
import com.ramm.cuscatlanpokemon.theme.AmberOrange
import com.ramm.cuscatlanpokemon.theme.ElectricPurple
import com.ramm.cuscatlanpokemon.theme.MediumGray

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ImageTypePokemon(
    imageUrl: String,
    types: List<TypesUseCaseInfo>
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        val imageSize = maxWidth * 0.8f
        val columnHeight = maxWidth * 0.9f

        Column(
            modifier = Modifier
                .height(columnHeight)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(imageSize),
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(types) { type ->
                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MediumGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = type.type.name,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ImageTypePokemonPreview() {
    ImageTypePokemon(
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/1.png",
        listOf(
            TypesUseCaseInfo(
                1,
                TypesTypeUseCaseInfo("Planta")
            ),
            TypesUseCaseInfo(
                2,
                TypesTypeUseCaseInfo("Venenoso")
            )
        )
    )
}
