package com.ronieathaydes.kontent.feature.settings.presentation.ui

import androidx.compose.foundation.layout.Box
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
import com.ronieathaydes.kontent.feature.settings.presentation.model.ConfigUiModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun ConfigRow(
    uiModel: ConfigUiModel,
) {
    Box {
        var isValueHidden by remember { mutableStateOf(true) }
        ListItem(
            headlineContent = {
                SecureTextField(
                    state = uiModel.value,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(
                            text = uiModel.name,
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                isValueHidden = !isValueHidden
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
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
                    textObfuscationMode = if (isValueHidden) {
                        TextObfuscationMode.RevealLastTyped
                    } else {
                        TextObfuscationMode.Visible
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
    )
}

class ConfigRowPreviewProvider : PreviewParameterProvider<ConfigUiModel> {
    override val values: Sequence<ConfigUiModel> =
        sequenceOf(
            config,
            configWithDescription,
        )
}

val config = ConfigUiModel(
    name = "Config name",
    key = "config.key",
    value = TextFieldState(initialText = ""),
    description = null,
)

val configWithDescription = ConfigUiModel(
    name = "Config name",
    key = "config.key",
    value = TextFieldState(
        initialText = "ConfigValue",
    ),
    description = "Config description",
)
