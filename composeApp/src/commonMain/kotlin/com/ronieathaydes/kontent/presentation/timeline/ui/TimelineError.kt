package com.ronieathaydes.kontent.presentation.timeline.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Error
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimelineError(
    uiState: Error,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = uiState.message,
        )
    }
}

@Preview
@Composable
private fun TimelineErrorPreview() {
    TimelineError(
        uiState = errorUiState,
    )
}

val errorUiState = Error(
    message = "Something went wrong",
)
