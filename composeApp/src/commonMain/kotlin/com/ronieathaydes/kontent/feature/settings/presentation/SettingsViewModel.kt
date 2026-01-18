package com.ronieathaydes.kontent.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronieathaydes.kontent.data.mastodon.provider.MastodonKontentProvider
import com.ronieathaydes.kontent.domain.model.Settings
import com.ronieathaydes.kontent.feature.settings.presentation.SettingsUiState.Loading
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingsViewModel(
    private val mastodonKontentProvider: MastodonKontentProvider,
    private val settingsUiStateMapper: SettingsUiStateMapper,
) : ViewModel() {

    private val configVisibilities = MutableStateFlow(emptyMap<String, Boolean>())

    val uiState: StateFlow<SettingsUiState> = combine(getContent(), configVisibilities, settingsUiStateMapper::map)
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = Loading,
        )

    private fun getContent(): Flow<Settings> =
        flow {
            emit(mastodonKontentProvider.getSettings())
        }

    fun onConfigVisibilityClick(key: String, isVisible: Boolean) {
        configVisibilities.update { map ->
            map.toMutableMap().apply {
                set(key, isVisible)
            }
        }
    }
}
