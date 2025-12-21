package com.ronieathaydes.kontent.feature.settings.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ronieathaydes.kontent.feature.settings.presentation.SettingsUiState.Content
import com.ronieathaydes.kontent.feature.settings.presentation.model.ConfigUiModel
import com.ronieathaydes.kontent.resources.Res
import com.ronieathaydes.kontent.resources.mastodon_config_access_token_description
import com.ronieathaydes.kontent.resources.mastodon_config_access_token_name
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun SettingsContent(
    uiState: Content,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        uiState.configs.forEach { config ->
            ConfigRow(
                uiModel = config,
            )
        }
    }
}

@Preview
@Composable
fun SettingsContentPreview(
    @PreviewParameter(provider = SettingsContentPreviewProvider::class) uiState: Content,
) {
    SettingsContent(
        uiState = uiState,
    )
}

class SettingsContentPreviewProvider : PreviewParameterProvider<Content> {
    override val values: Sequence<Content> =
        sequenceOf(
            contentUiState,
        )
}

val contentUiState = Content(
    configs = listOf(
        ConfigUiModel(
            name = Res.string.mastodon_config_access_token_name,
            key = "config.key",
            value = TextFieldState(
                initialText = "ConfigValue",
            ),
            description = Res.string.mastodon_config_access_token_description,
        ),
    ),
)
