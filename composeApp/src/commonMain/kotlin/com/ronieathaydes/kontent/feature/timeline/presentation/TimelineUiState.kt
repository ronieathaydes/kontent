package com.ronieathaydes.kontent.feature.timeline.presentation

import com.ronieathaydes.kontent.feature.timeline.presentation.model.StatusUiModel
import org.jetbrains.compose.resources.StringResource

sealed class TimelineUiState {
    data object Loading : TimelineUiState()

    data class Content(
        val isLoading: Boolean,
        val statuses: List<StatusUiModel>,
    ) : TimelineUiState()

    data class Error(
        val message: StringResource,
    ) : TimelineUiState()
}
