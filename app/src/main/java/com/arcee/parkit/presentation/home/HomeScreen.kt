package com.arcee.parkit.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.presentation.home.components.ProviderItem
import com.arcee.parkit.ui.theme.ParkItTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
    onSearchClicked: () -> Unit,
    onProviderClicked: (id: Int) -> Unit,
    onNotificationsClicked: () -> Unit
) {
    val providers = viewModel.providerPagingFlow.collectAsLazyPagingItems()

    HomeScreenContent(
        onSearchClicked = onSearchClicked,
        onProviderClicked = onProviderClicked,
        onNotificationsClicked = onNotificationsClicked,
        providers = providers
    )
}

@Composable
fun HomeScreenContent(
    onSearchClicked: () -> Unit,
    onProviderClicked: (id: Int) -> Unit,
    onNotificationsClicked: () -> Unit,
    providers: LazyPagingItems<Provider>
) {
    Box(
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = "Hi, John Doe",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                    Text("Thursday, 19 December 2024", style = MaterialTheme.typography.labelLarge)
                }
                OutlinedIconButton(
                    onClick = onNotificationsClicked, modifier = Modifier.padding(6.dp)
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                }
            }
            Box(modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .clickable { onSearchClicked() }) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp),
                ) {
                    Text(
                        text = "Park Easy & Safely",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(50))
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 9.dp)
                    ) {
                        Text(
                            text = "Find parking space", style = MaterialTheme.typography.labelSmall
                        )
                        Spacer(Modifier.weight(weight = 1f))
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(
                        text = "Parking Nearby", style = MaterialTheme.typography.titleMedium.merge(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Find your perfect spot",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                Spacer(Modifier.weight(weight = 1f))
                TextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "See More", style = MaterialTheme.typography.bodySmall.merge(
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
                if (providers.loadState.refresh == LoadState.Loading) {
                    item {
                        Text(
                            text = "Waiting for items to load from the backend",
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                } else {
                    items(
                        count = providers.itemCount,
                        key = providers.itemKey { it.id },
                    ) { index ->
                        val item = providers[index]
                        if (item != null) {
                            ProviderItem(data = item, onItemClick = onProviderClicked)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview(
    providers: LazyPagingItems<Provider> = flowOf(PagingData.empty<Provider>()).collectAsLazyPagingItems(),
    onSearchClicked: () -> Unit = {},
    onProviderClicked: (id: Int) -> Unit = {},
    onNotificationsClicked: () -> Unit = {},

    ) {
    ParkItTheme {
        HomeScreenContent(
            onSearchClicked = onSearchClicked,
            onProviderClicked = onProviderClicked,
            onNotificationsClicked = onNotificationsClicked,
            providers = providers
        )
    }
}