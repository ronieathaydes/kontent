package com.ronieathaydes.kontent.presentation.timeline.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Content
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Error
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Loading
import com.ronieathaydes.kontent.presentation.timeline.TimelineViewModel
import com.ronieathaydes.kontent.resources.Res
import com.ronieathaydes.kontent.resources.settings_title
import com.ronieathaydes.kontent.resources.timeline_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TimelineScreen(
    onSettingsClick: () -> Unit,
) {
    val viewModel = koinViewModel<TimelineViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TimelineScreen(
        uiState = uiState,
        onSettingsClick = onSettingsClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimelineScreen(
    uiState: TimelineUiState,
    onSettingsClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(
                            resource = Res.string.timeline_title,
                        ),
                    )
                },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(
                                resource = Res.string.settings_title,
                            ),
                        )
                    }
                },
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
        onSettingsClick = {},
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
