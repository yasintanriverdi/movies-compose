package com.yasintanriverdi.moviescompose.ui.layout

import androidx.annotation.StringRes
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Suppress("unused")
@Composable
fun RowSpacer(value: Int) = Spacer(modifier = Modifier.width(value.dp))

@Composable
fun ColumnSpacer(value: Int) = Spacer(modifier = Modifier.height(value.dp))

@Composable
fun LinkifyText(@StringRes textRes: Int, @StringRes urlRes: Int) {
    LinkifyText(
        text = stringResource(id = textRes),
        url = stringResource(id = urlRes)
    )
}

@Composable
fun LinkifyText(text: String, url: String) {
    val uriHandler = LocalUriHandler.current

    val layoutResult = remember {
        mutableStateOf<TextLayoutResult?>(null)
    }

    val annotatedStringBuilder = AnnotatedString.Builder()
    with(annotatedStringBuilder) {
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
    val annotatedString = annotatedStringBuilder.toAnnotatedString()

    Text(
        fontSize = 16.sp,
        text = annotatedString,
        modifier = Modifier.pointerInput(key1 = "gesture modifier $text") {
            detectTapGestures { offsetPosition ->
                layoutResult.value?.let {
                    val position = it.getOffsetForPosition(offsetPosition)
                    annotatedString.getStringAnnotations(position, position).firstOrNull()
                        ?.let { result ->
                            if (result.tag == "URL") {
                                uriHandler.openUri(result.item)
                            }
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
