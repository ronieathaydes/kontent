package com.ronieathaydes.kontent.feature.timeline.presentation

import com.ronieathaydes.kontent.domain.model.Status
import com.ronieathaydes.kontent.feature.timeline.presentation.model.StatusUiModelMapper
import org.koin.core.annotation.Factory

@Factory
class TimelineUiStateMapper(
    private val statusUiModelMapper: StatusUiModelMapper,
) {
    fun map(statuses: List<Status>): TimelineUiState =
        TimelineUiState.Content(
            statuses = statuses.map(statusUiModelMapper::map),
        )
}
