package com.ronieathaydes.kontent.feature.settings.presentation

import com.ronieathaydes.kontent.domain.model.Config
import com.ronieathaydes.kontent.feature.settings.presentation.model.ConfigUiModelMapper
import org.koin.core.annotation.Factory

@Factory
class SettingsUiStateMapper(
    private val configMapper: ConfigUiModelMapper,
) {
    fun map(configs: List<Config>): SettingsUiState =
        SettingsUiState.Content(
            configs = configs.map(configMapper::map),
        )
}
