package com.example.nav3.ui.theme.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneStrategy
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND

data class TwoPaneScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    val firstEntry: NavEntry<T>,
    val secondEntry: NavEntry<T>,
) : Scene<T> {
    override val entries: List<NavEntry<T>> = listOf(firstEntry, secondEntry)
    override val content: @Composable (() -> Unit) = {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                firstEntry.Content()
            }
            Box(modifier = Modifier.weight(1f)) {
                secondEntry.Content()
            }
        }
    }

    companion object {
        internal const val TWO_PANE_KEY = "TwoPane"

        fun twoPane() = mapOf(TWO_PANE_KEY to true)
    }
}

@Composable
fun <T : Any> rememberTwoPaneSceneStrategy(): SceneStrategy<T> {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    return remember(windowSizeClass) {
        SceneStrategy { entries ->
            if (!windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND)) {
                null
            } else {
                val lastTwoEntries = entries.takeLast(2)
                if (lastTwoEntries.size == 2 && lastTwoEntries.all {
                        it.metadata.contains(TwoPaneScene.TWO_PANE_KEY)
                    }) {
                    val firstEntry = lastTwoEntries.first()
                    val secondEntry = lastTwoEntries.last()

                    TwoPaneScene(
                        key = firstEntry to secondEntry,
                        previousEntries = entries.dropLast(1),
                        firstEntry = firstEntry,
                        secondEntry = secondEntry
                    )
                } else {
                    null
                }
            }
        }
    }
}
