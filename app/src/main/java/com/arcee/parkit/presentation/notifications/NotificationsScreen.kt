package com.arcee.parkit.presentation.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcee.parkit.presentation.notifications.components.NotificationItem

@Composable
fun NotificationsScreen() {
    val notifications = listOf(
        1, 2, 3, 4, 5, 6, 7, 8, 9
    )

    Box(
        modifier = Modifier.background(Color.White)
    ) {
        Column {
            Text(
                text = "Notifications",
                style = MaterialTheme.typography.headlineLarge.merge(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                itemsIndexed(notifications) { index, _p ->
                    NotificationItem(last = notifications.size == index + 1)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    NotificationsScreen()
}