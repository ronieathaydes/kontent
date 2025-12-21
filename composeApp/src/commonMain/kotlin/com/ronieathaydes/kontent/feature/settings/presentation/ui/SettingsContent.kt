package com.ronieathaydes.kontent.feature.settings.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ronieathaydes.kontent.feature.settings.presentation.SettingsUiState.Content
import com.ronieathaydes.kontent.feature.settings.presentation.model.ConfigUiModel
import com.ronieathaydes.kontent.resources.Res
import com.ronieathaydes.kontent.resources.mastodon_config_access_token_description
import com.ronieathaydes.kontent.resources.mastodon_config_access_token_name
import org.jetbrains.compose.resources.stringResource
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
            Box {
                var isHidden by remember { mutableStateOf(true) }
                ListItem(
                    headlineContent = {
                        SecureTextField(
                            state = config.value,
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(
                                    text = stringResource(config.name),
                                )
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        isHidden = !isHidden
                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                    )
                                }
                            },
                            supportingText = {
                                if (config.description != null) {
                                    Text(
                                        text = stringResource(config.description),
                                    )
                                }
                            },
                            textObfuscationMode = if (isHidden) {
                                TextObfuscationMode.RevealLastTyped
                            } else {
                                TextObfuscationMode.Visible
                            },
                        )
                    },
                )
            }
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
