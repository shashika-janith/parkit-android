package com.arcee.parkit.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arcee.parkit.presentation.components.Chip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProviderItem(
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            AsyncImage(
                model = "https://citations.robbinsparking.com/assets/images/2022-06-oie_jpg-1.png",
                contentDescription = null,
                modifier = Modifier
                    .width(90.dp)
                    .height(90.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    listOf("Car", "Lorry", "Bike").forEach { tag -> Chip(tag = tag) }
                }
                Text(
                    text = "Zero Hassle Parking", fontWeight = FontWeight.Bold,

                )
                Text(
                    text = "422 Fleet Street, Waumandee, Tennessee, 9695",
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
//        Text(
//            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
//            style = MaterialTheme.typography.bodyMedium
//        )
//        Text(
//            text = if (coin.isActive) "active" else "inactive",
//            color = if (coin.isActive) Color.Green else Color.Red,
//            fontStyle = FontStyle.Italic,
//            textAlign = TextAlign.End,
//            style = MaterialTheme.typography.bodySmall,
//            modifier = Modifier.align(Alignment.CenterVertically)
//        )
    }
}