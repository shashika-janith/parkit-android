package com.arcee.parkit.presentation.activities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcee.parkit.model.ParkingActivity
import com.arcee.parkit.presentation.activities.components.ActivityCard

@Composable
fun ActivitiesScreen(
    onActivityClicked: (id: Int, isActive: Boolean) -> Unit,
) {
    val activities = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9
    )

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Text(
                text = "Activities",
                style = MaterialTheme.typography.headlineLarge.merge(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(activities) { ele ->
                    ActivityCard(data = ParkingActivity(id = ele), onClicked = onActivityClicked)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityScreenPreview(
    onActivityClicked: (id: Int, isActive: Boolean) -> Unit = { _: Int, _: Boolean -> {}}
) {
    ActivitiesScreen(onActivityClicked = onActivityClicked)
}