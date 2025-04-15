package com.arcee.parkit.presentation.profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ProfileMetricCard(label: String, value: String) {
    ConstraintLayout(
        modifier = Modifier
            .padding(15.dp)
            .wrapContentHeight(),
    ) {
        // References
        val (labelRef, valueRef) = createRefs()

        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .constrainAs(valueRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(valueRef.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = label,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .constrainAs(labelRef) {
                    top.linkTo(valueRef.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileMetricCardPreview(label: String = "Earnings", value: String = "LKR 25,000") {
    ProfileMetricCard(label, value)
}
