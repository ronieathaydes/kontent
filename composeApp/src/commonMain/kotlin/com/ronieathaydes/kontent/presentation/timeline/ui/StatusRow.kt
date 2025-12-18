package com.ronieathaydes.kontent.presentation.timeline.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import coil3.compose.AsyncImage
import com.ronieathaydes.kontent.design.token.DimensionTokens
import com.ronieathaydes.kontent.presentation.timeline.model.StatusUiModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun StatusRow(uiModel: StatusUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = DimensionTokens.Spacing.medium),
        verticalArrangement = Arrangement.spacedBy(space = DimensionTokens.Spacing.small),
    ) {
        AuthorRow(
            authorAvatarUrl = uiModel.authorAvatarUrl,
            authorName = uiModel.authorName,
            authorUsername = uiModel.authorUsername,
        )
        if (uiModel.content != null) {
            Text(
                text = uiModel.content,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        if (uiModel.sharedStatus != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = DimensionTokens.Size.divider,
                        color = MaterialTheme.colorScheme.outline,
                        shape = MaterialTheme.shapes.small
                    ),
            ) {
                StatusRow(
                    uiModel = uiModel.sharedStatus,
                )
            }
        }
    }
}

@Composable
private fun AuthorRow(
    authorAvatarUrl: String,
    authorName: String,
    authorUsername: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = DimensionTokens.Spacing.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = authorAvatarUrl,
            contentDescription = null,
            modifier = Modifier
                .size(size = DimensionTokens.Size.avatar)
                .clip(CircleShape),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = DimensionTokens.Spacing.extraSmall),
        ) {
            Text(
                text = authorName,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = authorUsername,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatusRowPreview(
    @PreviewParameter(provider = StatusRowPreviewProvider::class) uiModel: StatusUiModel,
) {
    Box(
        modifier = Modifier.padding(all = DimensionTokens.Spacing.medium),
    ) {
        StatusRow(
            uiModel = uiModel,
        )
    }
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
    authorName = "Author 1",
    authorUsername = "author1",
    authorAvatarUrl = "authorAvatarUrl",
    sharedStatus = null,
)

val sharedStatus = StatusUiModel(
    content = AnnotatedString(text = "Status #2"),
    authorName = "Author 2",
    authorUsername = "author2",
    authorAvatarUrl = "authorAvatarUrl",
    sharedStatus = StatusUiModel(
        content = AnnotatedString(text = "Status #3"),
        authorName = "Author 3",
        authorUsername = "author3",
        authorAvatarUrl = "authorAvatarUrl",
        sharedStatus = null,
    ),
)
