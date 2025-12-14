package com.ronieathaydes.kontent

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ronieathaydes.kontent.presentation.timeline.TimelineViewModel
import com.ronieathaydes.kontent.presentation.timeline.ui.TimelineScreen

@Composable
fun App() {
    MaterialTheme {
        val viewModel = viewModel { TimelineViewModel() }
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        TimelineScreen(
            uiState = uiState,
        )
    }
}
