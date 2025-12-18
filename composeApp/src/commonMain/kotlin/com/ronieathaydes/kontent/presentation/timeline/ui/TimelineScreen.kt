package com.ronieathaydes.kontent.presentation.timeline.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Content
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Error
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Loading
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(
    uiState: TimelineUiState,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Kontent",
                    )
                }
            )
        },
        content = { paddingValues ->
            when (uiState) {
                Loading -> {
                    TimelineLoading(
                        modifier = Modifier.padding(paddingValues),
                    )
                }

                is Content -> {
                    TimelineContent(
                        uiState = uiState,
                        modifier = Modifier.padding(paddingValues),
                    )
                }

                is Error -> {
                    TimelineError(
                        uiState = uiState,
                        modifier = Modifier.padding(paddingValues),
                    )
                }
            }
        },
    )
}

@Preview
@Composable
private fun TimelineScreenPreview(
    @PreviewParameter(provider = TimelineScreenPreviewProvider::class) uiState: TimelineUiState,
) {
    TimelineScreen(
        uiState = uiState,
    )
}

class TimelineScreenPreviewProvider : PreviewParameterProvider<TimelineUiState> {
    override val values: Sequence<TimelineUiState> =
        sequenceOf(
            Loading,
            contentUiState,
            errorUiState,
        )
}
