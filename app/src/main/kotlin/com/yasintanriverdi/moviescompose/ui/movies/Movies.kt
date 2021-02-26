package com.yasintanriverdi.moviescompose.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.yasintanriverdi.moviescompose.ui.layout.ErrorItem
import com.yasintanriverdi.moviescompose.ui.layout.LoadingItem
import com.yasintanriverdi.moviescompose.ui.layout.LoadingView
import com.yasintanriverdi.moviescompose.ui.main.NavScreen
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.LocalWindowInsets
import dev.chrisbanes.accompanist.insets.toPaddingValues
import kotlinx.coroutines.flow.Flow

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

    LazyColumn(
        contentPadding = LocalWindowInsets.current.systemBars.toPaddingValues(top = false)
    ) {
        items(lazyMovieItems) { movie ->
            MovieItem(movie = movie!!, onMovieItemClick = onMovieItemClick)
            Divider()
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
    Surface(
        color = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        modifier = Modifier.clickable(onClick = { onMovieItemClick(movie.id) })
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovieImage(
                    BuildConfig.LARGE_IMAGE_URL + movie.backdropUrl,
                    modifier = Modifier.size(120.dp)
                )
                MovieTitle(
                    movie.title,
                    modifier = Modifier.weight(1f).padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun MovieImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    CoilImage(
        data = imageUrl,
        contentDescription = "Movie Item Image",
        modifier = modifier,
        fadeIn = true,
        contentScale = ContentScale.Crop,
        loading = {
            Image(
                painter = painterResource(id = R.drawable.ic_image),
                contentDescription = "Movie Item Loading",
                alpha = 0.45f
            )
        },
        error = {
            Image(
                painter = painterResource(id = R.drawable.ic_image_broken),
                contentDescription = "Movie Item Error",
                alpha = 0.45f
            )
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
