package com.arcee.parkit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable() (() -> Unit)? = null,
    placeholder: @Composable() (() -> Unit)? = null
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(12.dp)
            ),
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        label = null,
        trailingIcon = trailingIcon,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors().copy(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent, // Remove underline when focused
            unfocusedIndicatorColor = Color.Transparent, // Remove underline when not focused
            disabledIndicatorColor = Color.Transparent // Remove when disabled too)
        )
    )
}