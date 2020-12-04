package com.yasintanriverdi.moviescompose.ui.layout

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.UriHandlerAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    LazyColumnForIndexed(
        items = chunkedList,
        modifier = Modifier.padding(horizontal = hPadding.dp)
    ) { index, it ->
        if (index == 0) {
            columnSpacer(value = 8)
        }

        Row {
            it.forEachIndexed { rowIndex, item ->
                Box(
                    modifier = Modifier.weight(1F).align(Alignment.Top).padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    itemContent(item, index * rows + rowIndex)
                }
            }
            repeat(rows - it.size) {
                Box(modifier = Modifier.weight(1F).padding(8.dp)) {}
            }
        }
    }
}

@Composable
fun LinkifyText(@StringRes textRes: Int, @StringRes urlRes: Int) {
    LinkifyText(
        text = stringResource(id = textRes),
        url = stringResource(id = urlRes)
    )
}

@Composable
fun LinkifyText(text: String, url: String) {
    val uriHandler = UriHandlerAmbient.current

    val layoutResult = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }

    val annotatedString = annotatedString {
        pushStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        )
        append(text)
        addStringAnnotation(
            tag = "URL",
            annotation = url,
            start = 0,
            end = text.length
        )
    }
    Text(
        fontSize = 16.sp,
        text = annotatedString,
        modifier = Modifier.tapGestureFilter { offsetPosition ->
            layoutResult.value?.let {
                val position = it.getOffsetForPosition(offsetPosition)
                annotatedString.getStringAnnotations(position, position).firstOrNull()
                    ?.let { result ->
                        if (result.tag == "URL") {
                            uriHandler.openUri(result.item)
                        }
                    }
            }
        }.padding(top = 8.dp),
        onTextLayout = { layoutResult.value = it }
    )
}

@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = MaterialTheme.colors.primary)
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color.Red
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}
