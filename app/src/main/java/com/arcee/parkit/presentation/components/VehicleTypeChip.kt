package com.arcee.parkit.presentation.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcee.parkit.common.VehicleTypeEnum

@Composable
fun VehicleTypeChip(type: VehicleTypeEnum) {

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.surface)
            .padding(6.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.surfaceDim)
                .padding(9.dp)
        ) {
            Icon(
                painter = painterResource(id = VehicleTypeEnum.getVehicleTypeIcon(type)),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null,
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ActivityScreenPreview(
    type: VehicleTypeEnum = VehicleTypeEnum.CAR
) {
    VehicleTypeChip(type = type)
}