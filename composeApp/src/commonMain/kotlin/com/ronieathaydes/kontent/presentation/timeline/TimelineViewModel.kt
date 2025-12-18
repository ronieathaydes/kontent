package com.ronieathaydes.kontent.presentation.timeline

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import be.digitalia.compose.htmlconverter.HtmlStyle
import be.digitalia.compose.htmlconverter.htmlToAnnotatedString
import com.ronieathaydes.kontent.data.mastodon.MastodonRepository
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Content
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Error
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Loading
import com.ronieathaydes.kontent.presentation.timeline.model.StatusUiModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TimelineViewModel(
    private val repository: MastodonRepository,
) : ViewModel() {

    val uiState: StateFlow<TimelineUiState> = flow {
        emit(getContent())
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = Loading,
    )

    private suspend fun getContent(): TimelineUiState =
        repository.getHomeTimeline()
            .fold(
                onSuccess = { statuses ->
                    Content(
                        statuses = statuses.map { status ->
                            StatusUiModel(
                                content = htmlToAnnotatedString(
                                    html = status.content,
                                    style = HTML_STYLE,
                                ),
                            )
                        }
                    )
                },
                onFailure = { cause ->
                    Error(
                        message = cause.message ?: "Something went wrong",
                    )
                },
            )

    companion object {
        val HTML_STYLE = HtmlStyle(
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
