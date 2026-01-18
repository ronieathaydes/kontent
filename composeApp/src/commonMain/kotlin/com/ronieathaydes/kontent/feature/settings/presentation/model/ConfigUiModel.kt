package com.ronieathaydes.kontent.feature.settings.presentation.model

import androidx.compose.foundation.text.input.TextFieldState

data class ConfigUiModel(
    val name: String,
    val key: String,
    val value: TextFieldState,
    val description: String?,
    val isVisible: Boolean,
)
