package com.ronieathaydes.kontent.feature.timeline.presentation

import com.ronieathaydes.kontent.domain.model.Status
import com.ronieathaydes.kontent.feature.timeline.presentation.model.StatusUiModelMapper
import org.koin.core.annotation.Factory

@Factory
class TimelineUiStateMapper(
    private val statusUiModelMapper: StatusUiModelMapper,
) {
    fun map(isLoading: Boolean, statuses: List<Status>): TimelineUiState.Content =
        TimelineUiState.Content(
            isLoading = isLoading,
            statuses = statuses.map(statusUiModelMapper::map),
        )
}
