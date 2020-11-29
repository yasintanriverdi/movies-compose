package com.yasintanriverdi.moviescompose.ui.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.yasintanriverdi.moviescompose.R
import com.yasintanriverdi.moviescompose.ui.layout.MoviesAppBar

@Composable
fun About(navController: NavController) {
    val modif = Modifier.fillMaxWidth().fillMaxHeight()
    ConstraintLayout(modifier = modif) {
        Column {
            AboutAppBar(backClick = { navController.navigateUp() })
            Text("About")
        }
    }
}

@Composable
fun AboutAppBar(backClick: () -> Unit) {
    MoviesAppBar(title = {
        Text(
            text = stringResource(R.string.about),
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold
        )
    }, navigationIcon = {
        IconButton(onClick = { backClick() }) {
            Icon(
                Icons.Filled.ArrowBack,
                tint = MaterialTheme.colors.onPrimary
            )
        }
    })
}