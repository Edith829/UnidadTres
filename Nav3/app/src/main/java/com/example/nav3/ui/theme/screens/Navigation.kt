package com.example.nav3.screens

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.nav3.screens.detail.Detail
import com.example.nav3.screens.detail.DetailScreen
import com.example.nav3.screens.home.Home

@Composable
fun Navigation() {
    val backStack = rememberSaveable(
        saver = Saver(
            save = { ArrayList(it) },
            restore = { it.toMutableStateList() }
        )
    ) {
        mutableStateListOf<Any>(Home)
    }

    NavDisplay(
        backStack = backStack,
        transitionSpec = {
            ContentTransform(
                slideInHorizontally(initialOffsetX = { it }),
                slideOutHorizontally()
            )
        },
        popTransitionSpec = {
            ContentTransform(
                slideInHorizontally(),
                slideOutHorizontally(targetOffsetX = { it })
            )
        },
    ) { key ->
        when (key) {
            Home -> NavEntry(key) {
                Home(
                    onProductClick = { product ->
                        backStack.add(Detail(product.id))
                    }
                )
            }

            is Detail -> NavEntry(key) {
                DetailScreen(
                    productId = key.productId,
                    onBackClick = {
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    }
                )
            }

            else -> NavEntry(Unit) {
                Text(text = "Unknown")
            }
        }
    }
}
