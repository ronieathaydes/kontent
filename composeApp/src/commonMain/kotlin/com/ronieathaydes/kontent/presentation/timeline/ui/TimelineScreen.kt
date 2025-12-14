package com.ronieathaydes.kontent.presentation.timeline.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Content
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Error
import com.ronieathaydes.kontent.presentation.timeline.TimelineUiState.Loading
import com.ronieathaydes.kontent.presentation.timeline.model.StatusUiModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimelineScreen(
    uiState: TimelineUiState,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Kontent")
                }
            )
        },
        content = { paddingValues ->
            when (uiState) {
                Loading -> {
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Content -> {
                    LazyColumn(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize(),
                    ) {
                        items(uiState.statuses) { status ->
                            ListItem(
                                headlineContent = {
                                    Text(text = status.content)
                                },
                            )
                        }
                    }
                }

                is Error -> {
                    Box(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = uiState.message)
                    }
                }
            }
        },
    )
}

@Composable
@Preview
private fun TimelineScreenPreview() {
    val uiState = Content(
        statuses = listOf(
            StatusUiModel(
                content = AnnotatedString(text = "Status #1"),
            ),
            StatusUiModel(
                content = AnnotatedString(text = "Status #2"),
            ),
        ),
    )
    TimelineScreen(
        uiState = uiState,
    )
}
