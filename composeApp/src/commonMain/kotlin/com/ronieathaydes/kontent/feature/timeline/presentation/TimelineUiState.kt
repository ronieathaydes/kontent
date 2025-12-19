package com.ronieathaydes.kontent.feature.timeline.presentation

import com.ronieathaydes.kontent.feature.timeline.presentation.model.StatusUiModel

sealed class TimelineUiState {
    data object Loading : TimelineUiState()

    data class Content(
        val statuses: List<StatusUiModel>,
    ) : TimelineUiState()

    data class Error(
        val message: String,
    ) : TimelineUiState()
}
