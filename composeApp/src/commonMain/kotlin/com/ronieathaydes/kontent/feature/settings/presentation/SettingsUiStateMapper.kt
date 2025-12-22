package com.ronieathaydes.kontent.feature.settings.presentation

import com.ronieathaydes.kontent.domain.model.Settings
import com.ronieathaydes.kontent.feature.settings.presentation.model.SettingsUiModelMapper
import org.koin.core.annotation.Factory

@Factory
class SettingsUiStateMapper(
    private val settingsUiModelMapper: SettingsUiModelMapper,
) {
    fun map(settings: Settings): SettingsUiState =
        SettingsUiState.Content(
            settings = settingsUiModelMapper.map(settings),
        )
}
