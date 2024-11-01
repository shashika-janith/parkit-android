package com.arcee.parkit.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcee.parkit.presentation.home.components.ProviderItem
import com.arcee.parkit.ui.theme.ParkItTheme

@Composable
fun HomeScreen() {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent() {
    val providers = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9
    )

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = "Parking Nearby", fontWeight = FontWeight.Bold)
                    Text(
                        text = "Find your perfect spot",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                Spacer(Modifier.weight(weight = 1f))
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "See More",
                        style = MaterialTheme.typography.bodySmall.merge(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(9.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(providers) { _p ->
                    ProviderItem(onItemClick = {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    ParkItTheme {
        HomeScreenContent()
    }
}