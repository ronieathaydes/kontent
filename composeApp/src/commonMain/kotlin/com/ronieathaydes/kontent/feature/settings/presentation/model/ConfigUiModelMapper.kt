package com.ronieathaydes.kontent.feature.settings.presentation.model

import androidx.compose.foundation.text.input.TextFieldState
import com.ronieathaydes.kontent.domain.model.Config
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.Factory

@Factory
class ConfigUiModelMapper {
    suspend fun map(config: Config): ConfigUiModel =
        ConfigUiModel(
            name = getString(resource = config.name),
            key = config.key,
            value = TextFieldState(
                initialText = config.value,
            ),
            description = config.description?.let { getString(resource = it) },
        )
}
