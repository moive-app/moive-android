package com.moive.app.core.extensions

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter

@Composable
fun LazyListState.OnBottomReached(
    threshold: Int = 0,
    isLoading: Boolean,
    onLoadMore: () -> Unit,
) {
    require(threshold >= 0) { "threshold cannot be negative, but was $threshold" }

    val loadMore by rememberUpdatedState(onLoadMore)
    val loading by rememberUpdatedState(isLoading)

    val shouldLoadMore by remember {
        derivedStateOf {
            val total = layoutInfo.totalItemsCount
            if (total == 0 || loading) return@derivedStateOf false

            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            lastVisible + threshold >= total - 1
        }
    }

    LaunchedEffect(this) {
        snapshotFlow { shouldLoadMore }
            .distinctUntilChanged()
            .filter { it }
            .collect { loadMore() }
    }
}
