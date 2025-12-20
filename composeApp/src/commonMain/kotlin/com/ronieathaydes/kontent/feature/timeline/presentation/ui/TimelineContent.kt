package com.ronieathaydes.kontent.feature.timeline.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState.Content
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun TimelineContent(
    uiState: Content,
    modifier: Modifier = Modifier,
    onPullToRefresh: () -> Unit,
) {
    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = onPullToRefresh,
        modifier = modifier.fillMaxSize(),
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            itemsIndexed(uiState.statuses) { index, status ->
                StatusRow(
                    uiModel = status,
                )
                if (index < uiState.statuses.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimelineContentPreview(
    @PreviewParameter(provider = TimelineContentPreviewProvider::class) uiState: Content,
) {
    TimelineContent(
        uiState = uiState,
        onPullToRefresh = {},
    )
}

class TimelineContentPreviewProvider : PreviewParameterProvider<Content> {
    override val values: Sequence<Content> =
        sequenceOf(
            contentUiState,
        )
}

val contentUiState = Content(
    isLoading = true,
    statuses = listOf(
        status,
        sharedStatus,
    ),
)
