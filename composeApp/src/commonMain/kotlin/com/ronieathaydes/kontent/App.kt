package com.ronieathaydes.kontent

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ronieathaydes.kontent.presentation.timeline.TimelineViewModel
import com.ronieathaydes.kontent.presentation.timeline.ui.TimelineScreen
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.dsl.koinConfiguration
import org.koin.ksp.generated.configurationModules

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration {
            modules(AppKoinApplication.configurationModules)
        },
        content = {
            MaterialTheme {
                val viewModel = koinViewModel<TimelineViewModel>()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                TimelineScreen(
                    uiState = uiState,
                )
            }
        },
    )
}
