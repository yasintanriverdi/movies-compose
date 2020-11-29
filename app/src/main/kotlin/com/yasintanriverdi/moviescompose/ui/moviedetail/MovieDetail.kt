package com.yasintanriverdi.moviescompose.ui.moviedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yasintanriverdi.moviescompose.BuildConfig
import com.yasintanriverdi.moviescompose.R
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.UIState
import com.yasintanriverdi.moviescompose.ui.layout.NavigateBackAppBar
import com.yasintanriverdi.moviescompose.ui.movies.MoviesViewModel
import com.yasintanriverdi.moviescompose.ui.state.ErrorItem
import com.yasintanriverdi.moviescompose.ui.state.LoadingView
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun MovieDetail(
    navController: NavController,
    moviesViewModel: MoviesViewModel,
    movieId: String
) {

    moviesViewModel.fetchMovie(movieId)

    val stateFlow = moviesViewModel.viewStateFlow.collectAsState()
    val viewState = stateFlow.value

    when (viewState.uiState) {
        UIState.LOADING -> LoadingView(modifier = Modifier.fillMaxSize())
        UIState.CONTENT -> MovieDetailContent(
            backClick = { navController.navigateUp() },
            movie = viewState.movie
        )
        UIState.ERROR -> {
            ErrorItem(
                message = "Error occurred",
                onClickRetry = { moviesViewModel.fetchMovie(movieId) }
            )
        }
        UIState.IDLE -> {
            // do nothing
        }
    }
}

@Composable
fun MovieDetailContent(backClick: () -> Unit, movie: Movie?) {
    movie?.let {
        Column {
            MovieDetailAppBar(backClick = backClick, title = movie.title!!)
            MovieDetailImage(
                imageUrl = BuildConfig.LARGE_IMAGE_URL + movie.backdropUrl,
                modifier = Modifier.fillMaxWidth().height(240.dp)
            )
            ScrollableColumn {
                MovieDetailDescription(description = movie.overview!!)
            }
        }
    }
}

@Composable
fun MovieDetailAppBar(backClick: () -> Unit, title: String) {
    NavigateBackAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold
            )
        },
        backClick = backClick
    )
}

@Composable
fun MovieDetailImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    CoilImage(
        data = imageUrl,
        modifier = modifier,
        fadeIn = true,
        contentScale = ContentScale.Crop,
        loading = {
            Image(vectorResource(id = R.drawable.ic_image), alpha = 0.45f)
        },
        error = {
            Image(vectorResource(id = R.drawable.ic_image_broken), alpha = 0.45f)
        }
    )
}

@Composable
fun MovieDetailDescription(description: String) {
    Text(
        modifier = Modifier.padding(all = 16.dp),
        text = description,
        letterSpacing = TextUnit.Sp(2),
        color = MaterialTheme.colors.onBackground
    )
}