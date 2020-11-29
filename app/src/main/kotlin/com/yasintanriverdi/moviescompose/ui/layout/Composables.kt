package com.yasintanriverdi.moviescompose.ui.layout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("unused")
@Composable
fun rowSpacer(value: Int) = Spacer(modifier = Modifier.preferredWidth(value.dp))

@Composable
fun columnSpacer(value: Int) = Spacer(modifier = Modifier.preferredHeight(value.dp))

@Composable
fun <T> LazyGridFor(
    items: List<T> = listOf(),
    rows: Int = 3,
    hPadding: Int = 8,
    itemContent: @Composable LazyItemScope.(T, Int) -> Unit
) {
    val chunkedList = items.chunked(rows)
    LazyColumnForIndexed(items = chunkedList, modifier = Modifier.padding(horizontal = hPadding.dp)) { index, it ->
        if (index == 0) {
            columnSpacer(value = 8)
        }

        Row {
            it.forEachIndexed { rowIndex, item ->
                Box(modifier = Modifier.weight(1F).align(Alignment.Top).padding(8.dp), alignment = Alignment.Center) {
                    itemContent(item, index * rows + rowIndex)
                }
            }
            repeat(rows - it.size) {
                Box(modifier = Modifier.weight(1F).padding(8.dp)) {}
            }
        }
    }
}