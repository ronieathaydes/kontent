package com.ronieathaydes.kontent.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ronieathaydes.kontent.data.mastodon.provider.MastodonKontentProvider
import com.ronieathaydes.kontent.feature.settings.presentation.SettingsUiState.Loading
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingsViewModel(
    private val mastodonKontentProvider: MastodonKontentProvider,
    private val settingsUiStateMapper: SettingsUiStateMapper,
) : ViewModel() {

    val uiState: StateFlow<SettingsUiState> =
        flow {
            emit(getContent())
        }.stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = Loading,
        )

    private suspend fun getContent(): SettingsUiState =
        settingsUiStateMapper.map(
            settings = mastodonKontentProvider.getSettings(),
        )
}
