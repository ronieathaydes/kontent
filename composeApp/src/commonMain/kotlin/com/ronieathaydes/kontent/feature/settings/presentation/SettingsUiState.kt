package com.ronieathaydes.kontent.feature.settings.presentation

import com.ronieathaydes.kontent.feature.settings.presentation.model.SettingsUiModel

sealed class SettingsUiState {
    data object Loading : SettingsUiState()

    data class Content(
        val settings: SettingsUiModel,
    ) : SettingsUiState()
}
