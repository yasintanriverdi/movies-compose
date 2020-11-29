package com.yasintanriverdi.moviescompose.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.yasintanriverdi.moviescompose.BuildConfig
import com.yasintanriverdi.moviescompose.R
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.ui.main.NavScreen
import com.yasintanriverdi.moviescompose.ui.state.ErrorItem
import com.yasintanriverdi.moviescompose.ui.state.LoadingItem
import com.yasintanriverdi.moviescompose.ui.state.LoadingView
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
@Composable
fun Movies(
    navController: NavController,
    moviesViewModel: MoviesViewModel
) {

    MovieList(movies = moviesViewModel.movies) {
        navController.navigate("${NavScreen.MovieDetails.route}/$it")
    }
}

@Composable
fun MovieList(
    movies: Flow<PagingData<Movie>>,
    onMovieItemClick: (Int) -> Unit
) {
    val lazyMovieItems = movies.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyMovieItems) { movie ->
            MovieItem(movie = movie!!, onMovieItemClick = onMovieItemClick)
        }

        lazyMovieItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyMovieItems.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieItemClick: (Int) -> Unit) {
    Column(
        Modifier
            .clickable(onClick = { onMovieItemClick(movie.id) })
    ) {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MovieImage(
                BuildConfig.LARGE_IMAGE_URL + movie.backdropUrl,
                modifier = Modifier.preferredSize(120.dp)
            )
            MovieTitle(
                movie.title!!,
                modifier = Modifier.weight(1f).padding(start = 16.dp)
            )
        }

        Divider()
    }
}

@Composable
fun MovieImage(
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
fun MovieTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        maxLines = 2,
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis
    )
}