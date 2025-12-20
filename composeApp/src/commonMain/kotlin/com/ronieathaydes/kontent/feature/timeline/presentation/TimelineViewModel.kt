package com.ronieathaydes.kontent.feature.timeline.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronieathaydes.kontent.domain.model.Status
import com.ronieathaydes.kontent.domain.provider.KontentProvider
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState.Error
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState.Loading
import com.ronieathaydes.kontent.resources.Res
import com.ronieathaydes.kontent.resources.error_message
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@OptIn(ExperimentalCoroutinesApi::class)
@KoinViewModel
class TimelineViewModel(
    private val kontentProvider: KontentProvider,
    private val timelineUiStateMapper: TimelineUiStateMapper,
) : ViewModel() {

    private val refreshCount = MutableStateFlow(0)
    private val isLoading = MutableStateFlow(false)
    private val statuses: Flow<Result<List<Status>?>> = refreshCount
        .onEach {
            isLoading.emit(true)
        }.flatMapLatest {
            getContentFlow()
        }
        .onEach {
            isLoading.emit(false)
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = Result.success(null),
        )

    val uiState: StateFlow<TimelineUiState> =
        combine(isLoading, statuses) { isLoading, result ->
            val statuses = result.getOrThrow()
            if (statuses == null) {
                Loading
            } else {
                timelineUiStateMapper.map(isLoading, statuses)
            }
        }.catch {
            emit(Error(message = Res.string.error_message))
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = Loading,
        )

    private fun getContentFlow(): Flow<Result<List<Status>>> =
        flow {
            emit(kontentProvider.getHomeTimeline())
        }

    fun onPullToRefresh() {
        viewModelScope.launch {
            refreshCount.update { it.inc() }
        }
    }
}
