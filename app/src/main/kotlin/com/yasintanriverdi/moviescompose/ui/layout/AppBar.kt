package com.yasintanriverdi.moviescompose.ui.layout

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MoviesAppBar(
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        navigationIcon = navigationIcon,
        elevation = 6.dp,
        modifier = Modifier.preferredHeight(56.dp),
        title = title,
        actions = actions
    )
}

@Composable
fun NavigateBackAppBar(
    title: @Composable () -> Unit,
    backClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    MoviesAppBar(
        title = title,
        navigationIcon = {
            IconButton(onClick = { backClick() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        actions = actions)
}