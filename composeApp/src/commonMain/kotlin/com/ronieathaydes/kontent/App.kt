package com.ronieathaydes.kontent

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.ronieathaydes.kontent.presentation.settings.SettingsRoute
import com.ronieathaydes.kontent.presentation.settings.ui.SettingsScreen
import com.ronieathaydes.kontent.presentation.timeline.TimelineRoute
import com.ronieathaydes.kontent.presentation.timeline.ui.TimelineScreen
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration
import org.koin.ksp.generated.configurationModules

@Composable
fun App() {
    KoinApplication(
        configuration = koinConfiguration {
            modules(AppKoinApplication.configurationModules)
        },
        content = {
            MaterialTheme {
                val backStack = remember { mutableStateListOf<AppRoute>(TimelineRoute) }
                NavDisplay(
                    backStack = backStack,
                    onBack = { backStack.removeLastOrNull() },
                    entryProvider = entryProvider {
                        entry<TimelineRoute> {
                            TimelineScreen(
                                onSettingsClick = { backStack.add(SettingsRoute) },
                            )
                        }
                        entry<SettingsRoute> {
                            SettingsScreen(
                                onBackClick = { backStack.removeLastOrNull() },
                            )
                        }
                    },
                )
            }
        },
    )
}
