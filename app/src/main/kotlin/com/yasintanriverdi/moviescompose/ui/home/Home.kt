package com.yasintanriverdi.moviescompose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import com.yasintanriverdi.moviescompose.ui.layout.MoviesAppBar
import com.yasintanriverdi.moviescompose.ui.main.NavScreen
import com.yasintanriverdi.moviescompose.ui.movies.Movies
import com.yasintanriverdi.moviescompose.ui.movies.MoviesViewModel
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun Home(
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {
    Scaffold(
        topBar = { HomeAppBar { navController.navigate(NavScreen.About.route) } },
        modifier = Modifier.statusBarsPadding(),
    ) {
        val modifier = Modifier.padding(it)
        Column(
            modifier.fillMaxSize()
        ) {
            Movies(navController, moviesViewModel)
        }
    }
}

@Composable
fun HomeAppBar(aboutClick: () -> Unit) {
    MoviesAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(onClick = { aboutClick() }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Home Info",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
    )
}
