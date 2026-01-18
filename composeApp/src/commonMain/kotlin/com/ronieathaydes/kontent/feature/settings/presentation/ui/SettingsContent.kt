package com.ronieathaydes.kontent.feature.settings.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ronieathaydes.kontent.design.DimensionTokens
import com.ronieathaydes.kontent.feature.settings.presentation.SettingsUiState.Content
import com.ronieathaydes.kontent.feature.settings.presentation.model.ConfigUiModel
import com.ronieathaydes.kontent.feature.settings.presentation.model.SettingsUiModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun SettingsContent(
    uiState: Content,
    modifier: Modifier = Modifier,
    onConfigVisibilityClick: (String, Boolean) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = uiState.settings.title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(horizontal = DimensionTokens.Spacing.medium)
                .fillMaxWidth(),
        )
        uiState.settings.configs.forEach { config ->
            ConfigRow(
                uiModel = config,
                onVisibilityClick = onConfigVisibilityClick,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsContentPreview(
    @PreviewParameter(provider = SettingsContentPreviewProvider::class) uiState: Content,
) {
    SettingsContent(
        uiState = uiState,
        onConfigVisibilityClick = { _, _ -> },
    )
}

class SettingsContentPreviewProvider : PreviewParameterProvider<Content> {
    override val values: Sequence<Content> =
        sequenceOf(
            contentUiState,
        )
}

val contentUiState = Content(
    settings = SettingsUiModel(
        title = "Provider",
        configs = listOf(
            ConfigUiModel(
                name = "Config name",
                key = "config.key",
                value = TextFieldState(
                    initialText = "ConfigValue",
                ),
                description = "Config description",
                isVisible = false,
            ),
        ),
    ),
)
