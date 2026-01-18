package com.ronieathaydes.kontent.feature.settings.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ronieathaydes.kontent.feature.settings.presentation.model.ConfigUiModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun ConfigRow(
    uiModel: ConfigUiModel,
    onVisibilityChange: (String, Boolean) -> Unit,
) {
    Box {
        ListItem(
            headlineContent = {
                SecureTextField(
                    state = remember { uiModel.value },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = uiModel.name,
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { onVisibilityChange(uiModel.key, !uiModel.isVisible) },
                        ) {
                            Icon(
                                imageVector = if (uiModel.isVisible) {
                                    Icons.Filled.VisibilityOff
                                } else {
                                    Icons.Filled.Visibility
                                },
                                contentDescription = null,
                            )
                        }
                    },
                    supportingText = {
                        if (uiModel.description != null) {
                            Text(
                                text = uiModel.description,
                            )
                        }
                    },
                    textObfuscationMode = if (uiModel.isVisible) {
                        TextObfuscationMode.Visible
                    } else {
                        TextObfuscationMode.RevealLastTyped
                    },
                )
            },
        )
    }
}

@Preview
@Composable
private fun ConfigRowPreview(
    @PreviewParameter(provider = ConfigRowPreviewProvider::class) uiModel: ConfigUiModel,
) {
    ConfigRow(
        uiModel = uiModel,
        onVisibilityChange = { _, _ -> },
    )
}

class ConfigRowPreviewProvider : PreviewParameterProvider<ConfigUiModel> {
    override val values: Sequence<ConfigUiModel> =
        sequenceOf(
            config,
            configWithDescription,
            configWithValue,
            configWithValueVisible,
        )
}

val config = ConfigUiModel(
    name = "Config name",
    key = "config.key",
    value = TextFieldState(
        initialText = "",
    ),
    description = null,
    isVisible = false,
)

val configWithDescription = ConfigUiModel(
    name = "Config name",
    key = "config.key",
    value = TextFieldState(
        initialText = "",
    ),
    description = "Config description",
    isVisible = false,
)

val configWithValue = ConfigUiModel(
    name = "Config name",
    key = "config.key",
    value = TextFieldState(
        initialText = "ConfigValue",
    ),
    description = "Config description",
    isVisible = false,
)

val configWithValueVisible = ConfigUiModel(
    name = "Config name",
    key = "config.key",
    value = TextFieldState(
        initialText = "ConfigValue",
    ),
    description = "Config description",
    isVisible = true,
)
