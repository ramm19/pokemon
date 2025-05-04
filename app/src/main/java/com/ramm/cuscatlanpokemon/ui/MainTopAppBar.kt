package com.ramm.cuscatlanpokemon.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramm.cuscatlanpokemon.R
import com.ramm.cuscatlanpokemon.preferences
import com.ramm.cuscatlanpokemon.theme.RoyalBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    onProfileClick: () -> Unit = {},
    title: String = "Pokédex"
) {
    CenterAlignedTopAppBar(
        modifier = Modifier,
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = RoyalBlue,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            Image(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(44.dp),
                painter = painterResource(id = R.drawable.ic_pokemon),
                contentDescription = "Logo",
            )
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Column {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Perfil")
                    Text(
                        modifier = Modifier
                            .width(24.dp),
                        text = preferences.nameProfile,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    )

}
