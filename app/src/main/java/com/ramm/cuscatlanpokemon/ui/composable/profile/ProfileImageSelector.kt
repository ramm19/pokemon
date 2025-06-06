package com.ramm.cuscatlanpokemon.ui.composable.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ramm.cuscatlanpokemon.theme.LightGrey
import com.ramm.cuscatlanpokemon.theme.MediumGray
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.utils.FileManager.saveImageToInternalStorage
import java.io.File

@Composable
fun ProfileImageSelector(
    modifier: Modifier = Modifier,
    imagePath: String,
    onIntent: (PokemonIntent) -> Unit
) {
    val imageFile = File(imagePath)
    //var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        uri?.let {
            val savedPath = saveImageToInternalStorage(context, it)
            if (savedPath != null) {
                onIntent(PokemonIntent.Reduce.SetImagePath(savedPath))
                //imageUri = it
                onIntent(PokemonIntent.Screen.SaveImageProfile)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (imageFile.exists() == true) {
            Image(
                painter = rememberAsyncImagePainter(imageFile),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, MediumGray, CircleShape)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(LightGrey),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(64.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button ( onClick = {
            launcher.launch("image/*")
        }) {
            Text("Seleccionar Foto")
        }
    }
}

@Preview
@Composable
fun ProfileImageSelectorPreview() {
    ProfileImageSelector(Modifier, "") {}
}
