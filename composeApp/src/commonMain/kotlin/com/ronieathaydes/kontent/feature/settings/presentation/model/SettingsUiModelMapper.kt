package com.ronieathaydes.kontent.feature.settings.presentation.model

import com.ronieathaydes.kontent.domain.model.Settings
import org.koin.core.annotation.Factory

@Factory
class SettingsUiModelMapper(
    private val configMapper: ConfigUiModelMapper,
) {
    fun map(settings: Settings): SettingsUiModel =
        SettingsUiModel(
            title = settings.title,
            configs = settings.configs.map(configMapper::map),
        )
}
