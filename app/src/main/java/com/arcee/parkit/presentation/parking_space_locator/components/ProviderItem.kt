package com.arcee.parkit.presentation.parking_space_locator.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arcee.parkit.R
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.presentation.components.Chip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProviderItem(
    data: Provider,
    onBookmarkClicked: (id: Int) -> Unit,
    onPhoneClicked: (phoneNumber: String) -> Unit,
    onStartNavClicked: (lat: Double, lon: Double) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
//                AsyncImage(
//                    model = "https://citations.robbinsparking.com/assets/images/2022-06-oie_jpg-1.png",
//                    contentDescription = null,
//                    modifier = Modifier
//                        .width(90.dp)
//                        .height(90.dp)
//                )
                Image(
                    painter = painterResource(R.drawable.img_parking_area),
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
                        text = "LKR ${data.hourlyRate}/hr",
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
                        text = "6 Available",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelSmall.merge(fontWeight = FontWeight.SemiBold),
                    )
                }
            }
            Spacer(modifier = Modifier.height(9.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                OutlinedIconButton(onClick = {}, modifier = Modifier.padding(0.dp)) {
                    Icon(
                        imageVector = Icons.Rounded.Phone,
                        contentDescription = "Call",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                OutlinedIconButton(onClick = {}, modifier = Modifier.padding(0.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_bookmark_border_24),
                        contentDescription = "Bookmark",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                Button(
                    onClick = { onStartNavClicked(data.latitude, data.longitude) },
                    modifier = Modifier.fillMaxWidth(1f),
                ) {
                    Text(
                        text = "Start Navigation",
                        fontSize = 16.sp,
                        style = TextStyle(color = Color.White)
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
        id = 2L,
        name = "Central Mall Parking",
        address = "456 Elm St, Townsville",
        latitude = 40.7128,
        longitude = -74.0060,
        capacity = 30,
        hourlyRate = 150f,
        phone = "0715180287"
    ),
    onBookmarkClicked: (id: Int) -> Unit = {},
    onPhoneClicked: (phoneNumber: String) -> Unit = {},
    onStartNavClicked: (lat: Double, lon: Double) -> Unit = { lat, long -> }
) {
    ProviderItem(
        data = data,
        onBookmarkClicked = onBookmarkClicked,
        onPhoneClicked = onPhoneClicked,
        onStartNavClicked = onStartNavClicked
    )
}