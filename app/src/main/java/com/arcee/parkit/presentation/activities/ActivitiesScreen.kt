package com.arcee.parkit.presentation.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcee.parkit.presentation.activities.components.ActivityCard

@Composable
fun ActivitiesScreen() {
    val activities = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9
    )

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)

    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(activities) { _p ->
                ActivityCard()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityScreenPreview() {
    ActivitiesScreen()
}