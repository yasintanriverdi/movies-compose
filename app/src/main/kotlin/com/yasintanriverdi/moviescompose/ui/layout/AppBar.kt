package com.yasintanriverdi.moviescompose.ui.layout

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
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
    actions: @Composable RowScope.() -> Unit = {},
) {
    val backgroundColor =
        if (MaterialTheme.colors.isLight) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    val contentColor =
        if (MaterialTheme.colors.isLight) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
    TopAppBar(
        navigationIcon = navigationIcon,
        elevation = 6.dp,
        modifier = Modifier.height(56.dp),
        title = title,
        actions = actions,
        backgroundColor = backgroundColor,
        contentColor = contentColor
    )
}

@Composable
fun NavigateBackAppBar(
    title: @Composable () -> Unit,
    backClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val tintColor =
        if (MaterialTheme.colors.isLight) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
    MoviesAppBar(
        title = title,
        navigationIcon = {
            IconButton(onClick = { backClick() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Toolbar back",
                    tint = tintColor
                )
            }
        },
        actions = actions
    )
}
