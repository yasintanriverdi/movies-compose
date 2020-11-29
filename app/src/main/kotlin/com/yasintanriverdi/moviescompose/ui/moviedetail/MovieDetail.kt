package com.yasintanriverdi.moviescompose.ui.moviedetail

import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieDetail(modifier: Modifier? = null, text: String) {
    val modif = Modifier.fillMaxWidth().fillMaxHeight()
    ConstraintLayout(modifier = modif) {
        Text(text)
    }
}