package com.ramm.cuscatlanpokemon.ui.composable.profile

import android.app.DatePickerDialog
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.ramm.cuscatlanpokemon.theme.DarkNavyBlue
import com.ramm.cuscatlanpokemon.ui.composable.common.TextFieldProfile
import com.ramm.cuscatlanpokemon.ui.composable.common.TypeError
import com.ramm.cuscatlanpokemon.ui.interactions.PokemonIntent
import com.ramm.cuscatlanpokemon.ui.navigation.getDateFormatter
import com.ramm.cuscatlanpokemon.ui.viewstate.PokemonViewState
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar

@Composable
fun ProfileScreen(
    viewState: PokemonViewState,
    onIntent: (PokemonIntent) -> Unit,
    goBack: () -> Unit,
    goToDetail: () -> Unit
) {
    val context = LocalContext.current
    val documentAdultName = "DUI *"
    val documentChildName = "Carnet de minoridad"

    var nameEmptyError by remember { mutableStateOf(false) }
    var birthDateEmptyError by remember { mutableStateOf(false) }
    var documentEmptyError by remember { mutableStateOf(false) }
    var documentFormatError by remember { mutableStateOf(false) }

    val dateFormatter = getDateFormatter(viewState.birthDate)
    var isAdult = askIsAdult(
        day = dateFormatter?.dayOfMonth ?: 1,
        month = dateFormatter?.monthValue ?: 1,
        year = dateFormatter?.year ?: 2000
    )
    val calendar by remember { mutableStateOf(Calendar.getInstance()) }
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onIntent(
                    PokemonIntent.Reduce.SetBirthDate("$dayOfMonth/${month + 1}/$year")
                )
                documentEmptyError = false
                documentFormatError = false
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
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Perfil",
            style = MaterialTheme.typography.titleLarge
        )
        ProfileImageSelector(
            modifier = Modifier.padding(8.dp),
            viewState.imagePath,
            onIntent
        )
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
            modifier = Modifier
                .height(100.dp)
                .padding(top = 8.dp),
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
                .padding(top = 8.dp)
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
            modifier = Modifier
                .padding(top = 16.dp),
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

                    if (viewState.document.length == 9 && isAdult)
                        documentFormatError = false

                } else {
                    onIntent(
                        PokemonIntent.Reduce.SetDocument(it)
                    )
                }
            }
        )

        Button(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally),
            onClick = {
                if (viewState.name.isBlank())
                    nameEmptyError = true

                if (viewState.birthDate.isBlank())
                    birthDateEmptyError = true

                if (viewState.document.isBlank() && isAdult)
                    documentEmptyError = true

                if (viewState.document.length != 9 && isAdult)
                    documentFormatError = true

                when{
                    nameEmptyError || birthDateEmptyError || documentFormatError || documentEmptyError -> {
                        Toast.makeText(context, "Revisa los datos que estás ingresando.", Toast.LENGTH_SHORT).show()
                    }
                    viewState.imagePath.isBlank() -> {
                        Toast.makeText(context, "Debe seleccionar una foto.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        onIntent(PokemonIntent.Screen.SaveDataProfile)
                        goBack()
                    }
                }
            }
        ) {
            Text(text = "Guardar")
        }

        Text(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp),
            text = "Mis Pokémon",
            color = DarkNavyBlue,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.ExtraBold
        )

        ProfileMyTeam(
            modifier = Modifier
                .padding(bottom = 16.dp),
            viewState = viewState,
            onIntent = onIntent
        ) { goToDetail() }
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
    ProfileScreen(fakeViewState, {}, {}) {}
}
