package com.ronieathaydes.kontent.feature.timeline.presentation.ui

import androidx.compose.foundation.layout.Box
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
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState.Content
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState.Error
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState.Loading
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineViewModel
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
        onRefresh = viewModel::onRefresh,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimelineScreen(
    uiState: TimelineUiState,
    onSettingsClick: () -> Unit,
    onRefresh: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.timeline_title),
                    )
                },
                actions = {
                    IconButton(onClick = onSettingsClick) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(Res.string.settings_title),
                        )
                    }
                },
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues),
            ) {
                when (uiState) {
                    Loading -> {
                        TimelineLoading()
                    }

                    is Content -> {
                        TimelineContent(
                            uiState = uiState,
                            onPullToRefresh = onRefresh,
                        )
                    }

                    is Error -> {
                        TimelineError(
                            uiState = uiState,
                            onRetryClick = onRefresh,
                        )
                    }
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
        onRefresh = {},
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
