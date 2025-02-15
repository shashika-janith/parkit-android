package com.arcee.parkit.presentation.profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun InfoItem(label: String, value: String, icon: ImageVector) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {

        // References
        val (iconRef, labelRef, valueRef) = createRefs()

        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.constrainAs(iconRef) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.constrainAs(labelRef) {
                top.linkTo(parent.top)
                start.linkTo(iconRef.end, margin = 9.dp)
                bottom.linkTo(valueRef.top, margin = 3.dp)
            }
        )
        Text(
            text = value,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(valueRef) {
                top.linkTo(labelRef.bottom)
                start.linkTo(iconRef.end, margin = 9.dp)
                bottom.linkTo(parent.bottom)
            }

        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoItemPreview(
    label: String = "Name",
    value: String = "Chelle Bastian",
    icon: ImageVector = Icons.Rounded.Person
) {
    InfoItem(label, value, icon)
}