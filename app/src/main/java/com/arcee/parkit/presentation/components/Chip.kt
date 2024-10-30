package com.arcee.parkit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Chip(tag: String) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.background),
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp)
        ) {
            Text(
                text = tag,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}