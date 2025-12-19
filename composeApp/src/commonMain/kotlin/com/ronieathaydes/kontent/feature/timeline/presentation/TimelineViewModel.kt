package com.ronieathaydes.kontent.feature.timeline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronieathaydes.kontent.domain.provider.KontentProvider
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState.Error
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState.Loading
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TimelineViewModel(
    private val kontentProvider: KontentProvider,
    private val timelineUiStateMapper: TimelineUiStateMapper,
) : ViewModel() {

    val uiState: StateFlow<TimelineUiState> = flow {
        emit(getContent())
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = Loading,
    )

    private suspend fun getContent(): TimelineUiState =
        kontentProvider.getHomeTimeline()
            .fold(
                onSuccess = timelineUiStateMapper::map,
                onFailure = { cause -> Error(message = cause.message ?: "Something went wrong") },
            )
}
