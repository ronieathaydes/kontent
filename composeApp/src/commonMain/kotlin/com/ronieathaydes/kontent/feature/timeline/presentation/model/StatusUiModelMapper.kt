package com.ronieathaydes.kontent.feature.timeline.presentation.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.style.TextDecoration
import be.digitalia.compose.htmlconverter.HtmlStyle
import be.digitalia.compose.htmlconverter.htmlToAnnotatedString
import com.ronieathaydes.kontent.domain.model.Status
import org.koin.core.annotation.Factory

@Factory
class StatusUiModelMapper {
    fun map(status: Status): StatusUiModel =
        StatusUiModel(
            content = status.content?.let { htmlToAnnotatedString(html = status.content, style = HTML_STYLE) },
            authorName = status.author.displayName,
            authorUsername = status.author.username,
            authorAvatarUrl = status.author.avatarUrl,
            sharedStatus = status.sharedStatus?.let(::map),
        )

    companion object {
        private val HTML_STYLE = HtmlStyle(
            textLinkStyles = TextLinkStyles(
                style = SpanStyle(
                    color = Color(0xFF0066CC),
                    textDecoration = TextDecoration.Underline,
                ),
            ),
            isTextColorEnabled = true,
        )
    }
}
