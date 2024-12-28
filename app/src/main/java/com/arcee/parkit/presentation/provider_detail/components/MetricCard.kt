package com.arcee.parkit.presentation.provider_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcee.parkit.R

@Composable
fun MetricCard(icon: Painter, label: String, value: String) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(50))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 9.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.surfaceDim),
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            ) {
                Icon(
                    icon,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Spacer(modifier = Modifier.width(6.dp))
        Column {
            Text(
                text = value,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.merge(fontWeight = FontWeight.Bold),
            )
            Text(
                text = label,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall.merge(color = MaterialTheme.colorScheme.secondary),
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun MetricCardScreenPreview(
    icon: Painter = painterResource(R.drawable.baseline_local_parking_24),
    label: String = "Availability",
    value: String = "6 Slots Available"
) {
    MetricCard(icon, label, value)
}