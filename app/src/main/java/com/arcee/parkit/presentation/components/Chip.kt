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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Chip(tag: String, background: Color? = null, textStyle: TextStyle? = null) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(background ?: MaterialTheme.colorScheme.background)
            .padding(horizontal = 9.dp, vertical = 6.dp)
    ) {
        Text(
            text = tag,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall.merge(fontWeight = FontWeight.SemiBold)
                .merge(textStyle),
        )
    }
}