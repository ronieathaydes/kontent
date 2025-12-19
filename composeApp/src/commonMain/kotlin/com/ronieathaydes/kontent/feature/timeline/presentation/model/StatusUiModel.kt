package com.ronieathaydes.kontent.feature.timeline.presentation.model

import androidx.compose.ui.text.AnnotatedString

data class StatusUiModel(
    val content: AnnotatedString?,
    val authorName: String,
    val authorUsername: String,
    val authorAvatarUrl: String,
    val sharedStatus: StatusUiModel?,
)
