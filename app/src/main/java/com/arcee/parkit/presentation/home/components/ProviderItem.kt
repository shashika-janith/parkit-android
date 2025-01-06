package com.arcee.parkit.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arcee.parkit.R
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.presentation.components.Chip


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProviderItem(
    data: Provider,
    onItemClick: (id: Int) -> Unit
) {
    Box(modifier = Modifier
        .clip(shape = RoundedCornerShape(12.dp))
        .background(color = Color.White)
        .fillMaxWidth()
        .clickable { onItemClick(1) }) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
            Row(
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
                        listOf("Car", "Lorry", "Bike").forEach { tag ->
                            Chip(tag = tag)
                        }
                    }
                    Text(
                        text = data.name,
                        style = MaterialTheme.typography.bodyMedium.merge(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(
                        text = data.address,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Spacer(modifier = Modifier.height(9.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 9.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.coin_24),
                        tint = Color.Gray,
                        contentDescription = null,
                        modifier = Modifier.height(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "LKR 150.00/hr",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall.merge(fontWeight = FontWeight.SemiBold),
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 9.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.car_24),
                        tint = Color.Gray,
                        contentDescription = null,
                        modifier = Modifier.height(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "${data.availableSpots} Available",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall.merge(fontWeight = FontWeight.SemiBold),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProviderItemPreview(
    data: Provider = Provider(
        id = 1L,
        name = "Downtown Parking Garage",
        address = "123 Main St, Downtown",
        latitude = 40.7128F,
        longitude = -74.006F,
        capacity = 200,
        availableSpots = 25,
        contactNumber = "+1234567890",
        email = "info@downtownparking.com"
    ),
    onItemClick: (id: Int) -> Unit = {}
) {
    ProviderItem(
        data = data,
        onItemClick = onItemClick
    )
}