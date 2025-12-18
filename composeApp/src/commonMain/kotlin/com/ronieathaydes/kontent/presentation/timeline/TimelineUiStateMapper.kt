package com.ronieathaydes.kontent.presentation.timeline

import com.ronieathaydes.kontent.domain.model.Status
import com.ronieathaydes.kontent.presentation.timeline.model.StatusUiModelMapper
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
