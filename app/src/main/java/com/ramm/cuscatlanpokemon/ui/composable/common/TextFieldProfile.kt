package com.ramm.cuscatlanpokemon.ui.composable.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramm.cuscatlanpokemon.theme.BrightRedOrange
import com.ramm.cuscatlanpokemon.theme.DarkGray
import com.ramm.cuscatlanpokemon.theme.Transparent

@Composable
fun TextFieldProfile(
    modifier : Modifier = Modifier,
    enabled: Boolean = true,
    hint: String? = null,
    label: String? = null,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    isError: Boolean = false,
    typeError: TypeError? = null,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .border(width = 1.dp, color = DarkGray, shape = MaterialTheme.shapes.small),
        enabled = enabled,
        value = value,
        readOnly = readOnly,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        label = {
            label?.let {
                Text(text = it)
            }
        },
        placeholder = {
            Text(hint ?: "")
        },
        supportingText = {
            if (isError)
                Text(text = typeError?.message ?: "Error", color = BrightRedOrange)
        },
        isError = isError,
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Transparent,
            unfocusedContainerColor = Transparent,
            disabledIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent
        )
    )
}

enum class TypeError(val message: String) {
    REQUIRED("Campo requerido"),
    BAD_FORMAT_DUI("Formato inválido de DUI (00000000-0)")
}
