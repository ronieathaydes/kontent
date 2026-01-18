package com.ronieathaydes.kontent.feature.settings.presentation.model

import androidx.compose.foundation.text.input.TextFieldState
import com.ronieathaydes.kontent.domain.model.Config
import org.koin.core.annotation.Factory

@Factory
class ConfigUiModelMapper {
    fun map(config: Config, isVisible: Boolean): ConfigUiModel =
        ConfigUiModel(
            name = config.name,
            key = config.key,
            value = TextFieldState(
                initialText = config.value,
            ),
            description = config.description,
            isVisible = isVisible,
        )
}
