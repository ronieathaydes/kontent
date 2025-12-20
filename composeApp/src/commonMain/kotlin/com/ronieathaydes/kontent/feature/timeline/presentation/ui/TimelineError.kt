package com.ronieathaydes.kontent.feature.timeline.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ronieathaydes.kontent.design.DimensionTokens
import com.ronieathaydes.kontent.feature.timeline.presentation.TimelineUiState.Error
import com.ronieathaydes.kontent.resources.Res
import com.ronieathaydes.kontent.resources.error_message
import com.ronieathaydes.kontent.resources.try_again
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimelineError(
    uiState: Error,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(all = DimensionTokens.Spacing.medium),
    ) {
        Text(
            text = stringResource(uiState.message),
            modifier = Modifier.align(Alignment.Center),
        )
        Button(
            onClick = onRetryClick,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            Text(
                text = stringResource(Res.string.try_again),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimelineErrorPreview() {
    TimelineError(
        uiState = errorUiState,
        onRetryClick = {},
    )
}

val errorUiState = Error(
    message = Res.string.error_message,
)
