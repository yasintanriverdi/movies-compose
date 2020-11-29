package com.yasintanriverdi.moviescompose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.yasintanriverdi.moviescompose.R
import com.yasintanriverdi.moviescompose.ui.main.NavScreen
import com.yasintanriverdi.moviescompose.ui.movies.Movies
import com.yasintanriverdi.moviescompose.ui.movies.MoviesViewModel
import com.yasintanriverdi.moviescompose.ui.layout.MoviesAppBar

@Composable
fun Home(
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    val modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ConstraintLayout(modifier = modifier) {
        Column {
            HomeAppBar { navController.navigate(NavScreen.About.route) }
            Movies(navController, moviesViewModel)
        }
    }
}

@Composable
fun HomeAppBar(aboutClick: () -> Unit) {
    MoviesAppBar(title = {
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold
        )
    }, actions = {
        IconButton(onClick = { aboutClick() }) {
            Icon(
                Icons.Filled.Info,
                tint = MaterialTheme.colors.onPrimary
            )
        }
    })
}