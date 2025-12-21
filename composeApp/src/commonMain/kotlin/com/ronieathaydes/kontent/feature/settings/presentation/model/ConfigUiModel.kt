package com.ronieathaydes.kontent.feature.settings.presentation.model

import androidx.compose.foundation.text.input.TextFieldState
import org.jetbrains.compose.resources.StringResource

data class ConfigUiModel(
    val name: StringResource,
    val key: String,
    val value: TextFieldState,
    val description: StringResource?,
)
