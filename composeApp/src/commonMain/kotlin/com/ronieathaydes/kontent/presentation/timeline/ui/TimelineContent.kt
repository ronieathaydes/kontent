package com.ronieathaydes.kontent.presentation.timeline.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Content
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun TimelineContent(
    uiState: Content,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        itemsIndexed(uiState.statuses) { index, status ->
            StatusRow(
                uiModel = status
            )
            if (index < uiState.statuses.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}

@Preview
@Composable
private fun TimelineContentPreview(
    @PreviewParameter(provider = TimelineContentPreviewProvider::class) uiState: Content,
) {
    TimelineContent(
        uiState = uiState,
    )
}

class TimelineContentPreviewProvider : PreviewParameterProvider<Content> {
    override val values: Sequence<Content> =
        sequenceOf(
            contentUiState,
        )
}

val contentUiState = Content(
    statuses = listOf(
        status,
        sharedStatus,
    ),
)
