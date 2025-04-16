package com.arcee.parkit.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.arcee.parkit.domain.model.Provider
import com.arcee.parkit.presentation.components.ProviderItem

@Composable
fun ProviderList(
    lazyPagingItems: LazyPagingItems<Provider>,
    onProviderClicked: (data: Provider) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.id },
        ) { index ->
            val item = lazyPagingItems[index]
            if (item != null) {
                ProviderItem(data = item, onItemClick = onProviderClicked)
            }
        }
    }
}