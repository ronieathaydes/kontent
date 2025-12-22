package com.ronieathaydes.kontent.feature.settings.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.ronieathaydes.kontent.feature.settings.presentation.SettingsUiState
import com.ronieathaydes.kontent.feature.settings.presentation.SettingsUiState.Loading
import com.ronieathaydes.kontent.feature.settings.presentation.SettingsViewModel
import com.ronieathaydes.kontent.resources.Res
import com.ronieathaydes.kontent.resources.back
import com.ronieathaydes.kontent.resources.settings_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
) {
    val viewModel = koinViewModel<SettingsViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SettingsScreen(
        uiState = uiState,
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(resource = Res.string.back),
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(resource = Res.string.settings_title),
                    )
                },
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues),
            ) {
                when (uiState) {
                    Loading -> {
                        SettingsLoading()
                    }

                    is SettingsUiState.Content -> {
                        SettingsContent(
                            uiState = uiState,
                        )
                    }
                }
            }
        },
    )
}

@Preview
@Composable
private fun SettingsScreenPreview(
    @PreviewParameter(provider = SettingsScreenPreviewProvider::class) uiState: SettingsUiState,
) {
    SettingsScreen(
        uiState = uiState,
        onBackClick = {},
    )
}

class SettingsScreenPreviewProvider : PreviewParameterProvider<SettingsUiState> {
    override val values: Sequence<SettingsUiState> =
        sequenceOf(
            Loading,
            contentUiState,
        )
}
