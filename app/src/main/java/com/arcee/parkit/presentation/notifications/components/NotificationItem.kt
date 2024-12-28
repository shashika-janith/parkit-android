package com.arcee.parkit.presentation.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arcee.parkit.common.VehicleTypeEnum
import com.arcee.parkit.domain.model.Notification
import com.arcee.parkit.presentation.components.VehicleTypeChip

@Composable
fun NotificationItem(data: Notification, last: Boolean) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
            VehicleTypeChip(type = VehicleTypeEnum.LORRY)
            Spacer(modifier = Modifier.width(9.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "A new version of the app is available.",
                    style = MaterialTheme.typography.bodyMedium.merge(
                        fontWeight = FontWeight.SemiBold
                    ),
                )
                Text(
                    text = "Wednesday 9:31 PM",
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }

        if (!last) {
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.align(alignment = Alignment.BottomCenter),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationItemPreview(
    data: Notification = Notification(
        notificationId = 12345,
        title = "Parking Spot Reserved",
        message = "Your parking spot #101 has been successfully reserved.",
        timestamp = "2024-12-17T10:15:30Z",
        type = "reservation",
        isRead = false
    )
) {
    NotificationItem(last = false, data = data)
}