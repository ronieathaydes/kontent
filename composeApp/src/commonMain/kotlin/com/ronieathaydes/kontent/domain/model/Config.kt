package com.ronieathaydes.kontent.domain.model

import org.jetbrains.compose.resources.StringResource

data class Config(
    val name: StringResource,
    val key: String,
    val value: String,
    val description: StringResource?,
)
