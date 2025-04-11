package com.arcee.parkit.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.arcee.parkit.common.VehicleTypeEnum

@Composable
fun VehicleTypeInputChip(type: VehicleTypeEnum) {

    ConstraintLayout {
        val (labelRef, iconBoxRef) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(iconBoxRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(shape = RoundedCornerShape(9.dp))
                .border(
                    width = 1.dp,
                    color =  MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = RoundedCornerShape(9.dp)
                )
                .padding(18.dp)
        ) {
            Icon(
                painter = painterResource(id = VehicleTypeEnum.getVehicleTypeIcon(type)),
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                contentDescription = null,
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
                    .align(Alignment.Center)
            )
        }
        Text(
            stringResource(VehicleTypeEnum.getVehicleTypeLabel(type)),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(labelRef) {
                top.linkTo(iconBoxRef.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleTypeInputChipPreview(
    type: VehicleTypeEnum = VehicleTypeEnum.SCOOTER
) {
    VehicleTypeInputChip(type = type)
}