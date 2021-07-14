package com.yasintanriverdi.moviescompose.ui.moviedetail

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.insets.statusBarsPadding
import com.yasintanriverdi.moviescompose.BuildConfig
import com.yasintanriverdi.moviescompose.R
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.model.UIState
import com.yasintanriverdi.moviescompose.ui.layout.ErrorItem
import com.yasintanriverdi.moviescompose.ui.layout.LoadingView
import com.yasintanriverdi.moviescompose.ui.layout.NavigateBackAppBar
import com.yasintanriverdi.moviescompose.ui.movies.MoviesViewModel

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
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )

        Column(Modifier.verticalScroll(rememberScrollState())) {
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
    val painter = rememberCoilPainter(
        request = imageUrl,
        requestBuilder = {
            placeholder(R.drawable.ic_image)
            error(R.drawable.ic_image_broken)
        },
        fadeIn = true
    )

    Image(
        painter = painter,
        modifier = modifier,
        contentDescription = "Movie Detail Poster",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MovieDetailDescription(description: String) {
    Text(
        modifier = Modifier.padding(all = 16.dp),
        text = description,
        letterSpacing = 2.sp,
        color = MaterialTheme.colors.onBackground
    )
}
