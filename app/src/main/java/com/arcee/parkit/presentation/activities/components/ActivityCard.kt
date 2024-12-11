package com.arcee.parkit.presentation.activities.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcee.parkit.R

@Composable
fun ActivityCard(onClicked: (id: Int) -> Unit) {
    Box(

        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.White)
            .fillMaxWidth()
            .clickable { onClicked(1) }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Zero Hassle Parking",
                style = MaterialTheme.typography.bodyMedium.merge(
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(
                text = "422 Fleet Street, Waumandee, Tennessee, 9695",
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.height(15.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "15 October 2024, 03:31 PM",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.height(15.dp),
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "15 October 2024, 05:10 PM",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "LKR 120.00",
                    style = MaterialTheme.typography.bodyMedium.merge(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                    painter = painterResource(R.drawable.rounded_money_24),
                    contentDescription = null,
                    modifier = Modifier.height(15.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.weight(weight = 1f))
                VehicleTypeChip(type = "car")

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityCardPreview() {
    ActivityCard(onClicked = {})
}