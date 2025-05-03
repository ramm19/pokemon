package com.ramm.cuscatlanpokemon.ui.composable.profile

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramm.cuscatlanpokemon.ui.composable.common.TextFieldProfile
import com.ramm.cuscatlanpokemon.ui.composable.common.TypeError
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar

@Composable
fun ProfileScreen(
    viewState: PokemonViewState,
    onIntent: (PokemonIntent) -> Unit,
    goBack: () -> Unit
) {
    val context = LocalContext.current
    val documentAdultName = "DUI *"
    val documentChildName = "Carnet de minoridad"

    var nameEmptyError by remember { mutableStateOf(false) }
    var birthDateEmptyError by remember { mutableStateOf(false) }
    var documentEmptyError by remember { mutableStateOf(false) }
    var documentFormatError by remember { mutableStateOf(false) }

    var isAdult by remember { mutableStateOf(true) }
    val calendar by remember { mutableStateOf(Calendar.getInstance()) }
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onIntent(
                    PokemonIntent.Reduce.SetBirthDate("$dayOfMonth/${month + 1}/$year")
                )
                isAdult = askIsAdult(dayOfMonth, month + 1, year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.maxDate = System.currentTimeMillis()
        }
    }

    val document = viewState.document
    val formattedDocument = remember(document) {
        if (document.length <= 8 || !isAdult) {
            document
        } else {
            document.substring(0, 8) + "-" + document.substring(8)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Perfil",
            style = MaterialTheme.typography.titleLarge
        )
        ProfileImageSelector(viewState.imagePath, onIntent)
        TextFieldProfile(
            label = "Nombre *",
            hint = "Nombre",
            value = viewState.name,
            isError = nameEmptyError,
            typeError = TypeError.REQUIRED,
            onValueChange = {
                onIntent(
                    PokemonIntent.Reduce.SetName(it)
                )
                nameEmptyError = it.isEmpty()
            }
        )
        TextFieldProfile(
            modifier = Modifier.height(100.dp),
            label = "Pasatiempo favorito",
            hint = "Pasatiempo favorito",
            singleLine = false,
            maxLines = 3,
            value = viewState.hobby,
            onValueChange = {
                onIntent(
                    PokemonIntent.Reduce.SetHobby(it)
                )
            }
        )
        TextFieldProfile(
            modifier = Modifier
                .clickable {
                    datePickerDialog.show()
                },
            enabled = false,
            label = "Fecha de nacimiento *",
            hint = "Fecha de nacimiento",
            value = viewState.birthDate,
            isError = birthDateEmptyError,
            typeError = TypeError.REQUIRED,
            onValueChange = {
                onIntent(
                    PokemonIntent.Reduce.SetBirthDate(it)
                )
                birthDateEmptyError = it.isEmpty()
            }
        )
        TextFieldProfile(
            keyboardOptions = if (isAdult)
                    KeyboardOptions(keyboardType = KeyboardType.Number)
                else
                    KeyboardOptions.Default,
            label = if (isAdult) documentAdultName else documentChildName,
            hint = if (isAdult) documentAdultName else documentChildName,
            value = formattedDocument,
            isError = documentEmptyError || documentFormatError,
            typeError = if (documentEmptyError) TypeError.REQUIRED else TypeError.BAD_FORMAT_DUI,
            onValueChange = {
                if (isAdult) {
                    val digitsOnly = it.filter { it.isDigit() }
                    documentEmptyError = digitsOnly.isEmpty()
                    if (digitsOnly.length <= 9) {
                        onIntent(
                            PokemonIntent.Reduce.SetDocument(digitsOnly)
                        )
                    }
                } else {
                    onIntent(
                        PokemonIntent.Reduce.SetDocument(it)
                    )
                }
            }
        )

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                if (!nameEmptyError && !birthDateEmptyError) {
                    onIntent(PokemonIntent.Screen.SaveDataProfile)
                    goBack()
                }
            }
        ) {
            Text(text = "Guardar")
        }
    }
}

fun askIsAdult (day: Int, month: Int, year: Int): Boolean {
    val birthday = LocalDate.of(year, month, day)
    val today = LocalDate.now()
    val edge = ChronoUnit.YEARS.between(birthday, today)
    return edge >= 18
}

@Preview
@Composable
fun ProfileScreenPreview() {
    val fakeViewState = PokemonViewState()
    ProfileScreen(fakeViewState, {}) {}
}
