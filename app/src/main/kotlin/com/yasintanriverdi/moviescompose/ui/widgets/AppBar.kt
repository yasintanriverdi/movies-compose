package com.yasintanriverdi.moviescompose.ui.widgets

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.TopAppBar
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