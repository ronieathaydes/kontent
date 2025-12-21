package com.ronieathaydes.kontent.feature.settings.presentation

import com.ronieathaydes.kontent.feature.settings.presentation.model.ConfigUiModel

sealed class SettingsUiState {
    data object Loading : SettingsUiState()

    data class Content(
        val configs: List<ConfigUiModel>,
    ) : SettingsUiState()
}
