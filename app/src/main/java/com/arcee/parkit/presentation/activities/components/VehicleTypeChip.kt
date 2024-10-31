package com.arcee.parkit.presentation.activities.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arcee.parkit.R

@Composable
fun VehicleTypeChip(type: String) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.background),
    ) {
        Box(
            modifier = Modifier
                .width(36.dp)
                .height(36.dp)
                .background(MaterialTheme.colorScheme.secondary)

        ) {
            Icon(
                painter = painterResource(id = R.drawable.car_24),
                contentDescription = null,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .align(Alignment.Center)
            )
        }
    }
}