package com.arcee.parkit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.arcee.parkit.R
import com.arcee.parkit.domain.model.Favorite

@Composable
fun FavoriteItem(
    data: Favorite,
    onItemClick: (data: Favorite) -> Unit,
    onRemoveFromFavorite: (data: Favorite) -> Unit,
) {
    Box(modifier = Modifier
        .clip(shape = RoundedCornerShape(12.dp))
        .background(color = Color.White)
        .fillMaxWidth()
        .clickable { onItemClick(data) }) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = "https://citations.robbinsparking.com/assets/images/2022-06-oie_jpg-1.png",
                    contentDescription = null,
                    modifier = Modifier
                        .width(66.dp)
                        .height(66.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.weight(1f)
                ) {
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
                IconButton(
                    onClick = { onRemoveFromFavorite(data) },
                    modifier = Modifier.padding(0.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_bookmark_border_24),
                        contentDescription = "Bookmark",
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoriteItemPreview(
    data: Favorite = Favorite(
        id = 1,
        entityId = 1,
        name = "Downtown Parking Garage",
        address = "123 Main St, Downtown",
        latitude = 40.7128,
        longitude = -74.006,
        capacity = 200,
        phone = "+1234567890",
        hourlyRate = 150.00F,
        images = arrayOf(),
    ),
    onItemClick: (data: Favorite) -> Unit = {},
    onRemoveFromFavorite: (data: Favorite) -> Unit = {}
) {
    FavoriteItem(
        data = data,
        onItemClick = onItemClick,
        onRemoveFromFavorite = onRemoveFromFavorite
    )
}