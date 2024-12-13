package com.arcee.parkit.presentation.active_session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.arcee.parkit.presentation.active_session.components.InfoCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveSessionScreen(
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
                Row(
                    modifier = Modifier.padding(top = 12.dp, bottom = 21.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(27.dp)
                        )
                    }
                    Text(
                        text = "Parking Session",
                        style = MaterialTheme.typography.headlineSmall.merge(fontWeight = FontWeight.Bold),
                    )
                }
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

                Spacer(modifier = Modifier.height(9.dp))

                InfoCard(
                    icon = painterResource(R.drawable.baseline_timer_24),
                    label = "Parking Time",
                    value = "00:00:30"
                )

                Spacer(modifier = Modifier.height(9.dp))

                InfoCard(
                    icon = painterResource(R.drawable.coin_24),
                    label = "Current Cost",
                    value = "Rs.120.00"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActiveSessionScreenPreview() {
    ActiveSessionScreen(onNavigateBack = {})
}