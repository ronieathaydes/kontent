package com.ronieathaydes.kontent.presentation.timeline.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.ronieathaydes.kontent.presentation.timeline.model.StatusUiModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun StatusRow(uiModel: StatusUiModel) {
    ListItem(
        headlineContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            ) {
                Text(
                    text = uiModel.author,
                )
                if (uiModel.content != null) {
                    Text(
                        text = uiModel.content,
                    )
                }
                if (uiModel.sharedStatus != null) {
                    SharedStatusRow(
                        uiModel.sharedStatus,
                    )
                }
            }
        },
    )
}

@Composable
private fun SharedStatusRow(uiModel: StatusUiModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp,
                ),
            ),
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        ) {
            Text(
                text = uiModel.author,
            )
            if (uiModel.content != null) {
                Text(text = uiModel.content)
            }
        }
    }
}

@Preview
@Composable
private fun StatusRowPreview(
    @PreviewParameter(provider = StatusRowPreviewProvider::class) uiModel: StatusUiModel,
) {
    StatusRow(
        uiModel = uiModel,
    )
}

class StatusRowPreviewProvider : PreviewParameterProvider<StatusUiModel> {
    override val values: Sequence<StatusUiModel> =
        sequenceOf(
            status,
            sharedStatus,
        )
}

val status = StatusUiModel(
    content = AnnotatedString(text = "Status #1"),
    author = "Author 1 (author1)",
    sharedStatus = null,
)

val sharedStatus = StatusUiModel(
    content = AnnotatedString(text = "Status #2"),
    author = "Author 2 (author2)",
    sharedStatus = StatusUiModel(
        content = AnnotatedString(text = "Status #3"),
        author = "Author 3 (author3)",
        sharedStatus = null,
    ),
)
