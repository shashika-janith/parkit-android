package com.arcee.parkit.presentation.activity_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arcee.parkit.R
import com.arcee.parkit.presentation.activity_detail.components.InfoRow
import com.arcee.parkit.presentation.components.ScreenHeader

@Composable
fun ActivityDetailScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                ScreenHeader(onNavigateBack = onNavigateBack, title = "Activity Details")
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = "https://citations.robbinsparking.com/assets/images/2022-06-oie_jpg-1.png",
                        contentDescription = null,
                        modifier = Modifier
                            .width(47.dp)
                            .height(48.dp)
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Text(
                            text = "Zero Hassle Parking",
                            style = MaterialTheme.typography.bodyMedium.merge(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Text(
                            text = "422 Fleet Street, Waumandee, Tennessee, 9695",
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 15.dp)
                )
                InfoRow(label = "Date", value = "15 May 2024")
                InfoRow(label = "Parking Slot", value = "F3-B-33")
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 15.dp)
                )
                InfoRow(label = "Arrived", value = "10.15 AM")
                InfoRow(label = "Exit", value = "11.00 AM")
                InfoRow(label = "Duration", value = "1 Hour")
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 15.dp)
                )
                InfoRow(label = "Sub Total", value = "LKR 100.00")
                InfoRow(label = "Discount", value = "LKR -0.00")
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 15.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Text(
                        text = "Total",
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Text(
                        text = "LKR 100.00",
                        style = MaterialTheme.typography.bodyLarge.merge(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(vertical = 15.dp)
                )
                Column {
                    Text(
                        text = "Paid By",
                        style = MaterialTheme.typography.labelSmall,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                    ) {
                        Text(
                            text = "Cash",
                            style = MaterialTheme.typography.bodyLarge.merge(
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            painter = painterResource(R.drawable.rounded_money_24),
                            contentDescription = null,
                            modifier = Modifier
                                .height(30.dp)
                                .aspectRatio(1.0f),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(Modifier.weight(weight = 1f))
                        Text(
                            text = "LKR 100.00",
                            style = MaterialTheme.typography.bodyLarge.merge(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityDetailScreenPreview() {
    ActivityDetailScreen(onNavigateBack = {})
}