package com.yasintanriverdi.moviescompose.ui.movies

import androidx.compose.foundation.ClickableText
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.compose.navigate
import androidx.navigation.NavController
import com.yasintanriverdi.moviescompose.ui.main.NavScreen

// TODO - add methods for main
@Composable
fun Movies(
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {

    // TODO - get result
    moviesViewModel.fetchMovies()

    val modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ConstraintLayout(modifier = modifier) {
        ClickableText(text = AnnotatedString("Coming Soon"), onClick = {
            navController.navigate("${NavScreen.MovieDetails.route}/$it")
        })
    }
}