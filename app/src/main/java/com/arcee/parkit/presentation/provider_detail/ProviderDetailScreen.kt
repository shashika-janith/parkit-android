package com.arcee.parkit.presentation.provider_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arcee.parkit.R
import com.arcee.parkit.common.VehicleTypeEnum
import com.arcee.parkit.presentation.components.VehicleTypeChip
import com.arcee.parkit.presentation.provider_detail.components.MetricCard
import com.arcee.parkit.ui.theme.ParkItTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProviderDetailScreen(onStartNavClicked: (lat: Double, lon: Double) -> Unit) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeContent),
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 9.dp)
            ) {
                OutlinedIconButton(
                    onClick = { /*TODO*/ },
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
                Text(
                    text = "Activity Details",
                    style = MaterialTheme.typography.headlineSmall.merge(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(40.dp)) // Used to center the 'Text' horizontally.
            }
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(horizontal = 9.dp, vertical = 6.dp)
            ) {
                OutlinedIconButton(onClick = {}, modifier = Modifier.padding(0.dp)) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_bookmark_border_24),
                        contentDescription = "Bookmark",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                Button(
                    onClick = { onStartNavClicked(6.9281316, 79.8426634) },
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
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                Spacer(modifier = Modifier.height(6.dp))
                AsyncImage(
                    model = "https://citations.robbinsparking.com/assets/images/2022-06-oie_jpg-1.png",
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
//                        .aspectRatio(ratio = 0.3f, matchHeightConstraintsFirst = false)
                        .height(240.dp)
                        .clip(shape = RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(9.dp))
                Text(
                    text = "Zero Hassle Parking",
                    style = MaterialTheme.typography.titleLarge.merge(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = "422 Fleet Street, Waumandee, Tennessee, 9695",
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 9.dp)
                )

                Text(
                    text = "Vehicle Types",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.merge(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 3.dp)
                )

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    listOf("CAR", "LORRY", "SCOOTER").forEach { type ->
                        VehicleTypeChip(type = VehicleTypeEnum.CAR)
                    }
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 9.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MetricCard(
                        icon = painterResource(R.drawable.baseline_local_parking_24),
                        label = "Availability",
                        value = "6 Slots Available"
                    )
                    MetricCard(
                        icon = painterResource(R.drawable.coin_24),
                        label = "Parking Rate",
                        value = "LKR 150.00 / hour"
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    MetricCard(
                        icon = painterResource(R.drawable.baseline_flag_24),
                        label = "Type",
                        value = "Building"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProviderDetailScreenPreview(
    onStartNavClicked: (lat: Double, lon: Double) -> Unit = { lat, lon ->
        println("")
    }
) {
    ParkItTheme {
        ProviderDetailScreen(onStartNavClicked = onStartNavClicked)
    }
}