package com.arcee.parkit.presentation.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.arcee.parkit.common.Resource
import com.arcee.parkit.data.mappers.toProvider
import com.arcee.parkit.domain.model.Favorite
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.presentation.components.FavoriteItem

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel = hiltViewModel<FavouritesViewModel>(),
    onProviderClicked: (data: Provider) -> Unit,
) {
    val providersState by viewModel.stateFlow.collectAsState()

    fun handleClickProvider(data: Favorite) {
        val provider = data.toProvider()
        onProviderClicked(provider)
    }

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Column {
            Text(
                text = "Favourites",
                style = MaterialTheme.typography.headlineLarge.merge(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            )
            when (providersState) {
                is Resource.Error -> {}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val providerFlow = (providersState as Resource.Success).data
                    val providers = providerFlow?.collectAsLazyPagingItems()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),

                        ) {
                        if (providers != null) {
                            items(
                                count = providers.itemCount,
                                key = providers.itemKey { it.id },
                            ) { index ->
                                val item = providers[index]
                                if (item != null) {
                                    FavoriteItem(
                                        data = item,
                                        onItemClick = { data: Favorite -> handleClickProvider(data) })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}