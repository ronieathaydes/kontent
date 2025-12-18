package com.ronieathaydes.kontent.presentation.timeline.model

import androidx.compose.ui.text.AnnotatedString

data class StatusUiModel(
    val content: AnnotatedString?,
    val authorName: String,
    val authorUsername: String,
    val authorAvatarUrl: String,
    val sharedStatus: StatusUiModel?,
)
