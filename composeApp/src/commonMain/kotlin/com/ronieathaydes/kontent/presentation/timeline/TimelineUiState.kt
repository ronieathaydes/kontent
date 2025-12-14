package com.ronieathaydes.kontent.presentation.timeline

import com.ronieathaydes.kontent.presentation.timeline.model.StatusUiModel

sealed class TimelineUiState {
    data object Loading : TimelineUiState()

    data class Content(
        val statuses: List<StatusUiModel>,
    ) : TimelineUiState()

    data class Error(
        val message: String,
    ) : TimelineUiState()
}
