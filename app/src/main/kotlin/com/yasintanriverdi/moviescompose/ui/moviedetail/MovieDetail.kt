package com.yasintanriverdi.moviescompose.ui.moviedetail

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.yasintanriverdi.moviescompose.ui.layout.ErrorItem
import com.yasintanriverdi.moviescompose.ui.layout.LoadingView
import com.yasintanriverdi.moviescompose.ui.layout.NavigateBackAppBar
import com.yasintanriverdi.moviescompose.ui.movies.MoviesViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.AmbientWindowInsets
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import dev.chrisbanes.accompanist.insets.toPaddingValues

@Composable
fun MovieDetail(
    navController: NavController,
    moviesViewModel: MoviesViewModel,
    movieId: String
) {

    val viewState by moviesViewModel.viewStateFlow.collectAsState()
    moviesViewModel.fetchMovie(movieId)

    Scaffold(
        topBar = {
            MovieDetailAppBar(
                backClick = { navController.navigateUp() },
                title = viewState.movie?.title ?: ""
            )
        },
        modifier = Modifier.statusBarsPadding(),
    ) {
        val modifier = Modifier.padding(it)
        Crossfade(viewState.uiState) { uiState ->
            when (uiState) {
                UIState.LOADING -> LoadingView(modifier = modifier.fillMaxSize())
                UIState.CONTENT -> MovieDetailContent(viewState.movie!!, modifier = modifier)
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
    }
}

@Composable
fun MovieDetailContent(movie: Movie, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        MovieDetailImage(
            imageUrl = BuildConfig.LARGE_IMAGE_URL + movie.backdropUrl,
            modifier = Modifier.fillMaxWidth().height(240.dp)
        )
        ScrollableColumn(
            contentPadding = AmbientWindowInsets.current.systemBars.toPaddingValues(
                top = false
            )
        ) {
            MovieDetailDescription(description = movie.overview!!)
        }
    }
}

@Composable
fun MovieDetailAppBar(backClick: () -> Unit, title: String) {
    NavigateBackAppBar(
        title = {
            Text(
                text = title,
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
