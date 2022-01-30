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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.yasintanriverdi.moviescompose.BuildConfig
import com.yasintanriverdi.moviescompose.R
import com.yasintanriverdi.moviescompose.model.Movie
import com.yasintanriverdi.moviescompose.ui.layout.ErrorItem
import com.yasintanriverdi.moviescompose.ui.layout.LoadingItem
import com.yasintanriverdi.moviescompose.ui.layout.LoadingView
import com.yasintanriverdi.moviescompose.ui.main.NavScreen
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
        Modifier.padding(
            rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.systemBars, applyTop = false
            )
        )
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
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
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
    val painter = rememberImagePainter(
        data = imageUrl,
        builder = {
            crossfade(true)
            placeholder(R.drawable.ic_image)
            error(R.drawable.ic_image_broken)
        }
    )

    Image(
        painter = painter,
        modifier = modifier,
        contentDescription = "Movie Item Image",
        contentScale = ContentScale.Crop
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
