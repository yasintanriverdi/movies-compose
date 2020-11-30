package com.yasintanriverdi.moviescompose.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.yasintanriverdi.moviescompose.ui.about.About
import com.yasintanriverdi.moviescompose.ui.home.Home
import com.yasintanriverdi.moviescompose.ui.main.NavScreen.MovieDetails.argMovieId
import com.yasintanriverdi.moviescompose.ui.moviedetail.MovieDetail
import com.yasintanriverdi.moviescompose.ui.movies.MoviesViewModel

@Composable
fun Main(
    moviesViewModel: MoviesViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            Home(navController, moviesViewModel)
        }
        composable(
            NavScreen.MovieDetails.routeWithArgument,
            arguments = listOf(navArgument(argMovieId) { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(argMovieId)?.let {
                MovieDetail(
                    navController = navController,
                    moviesViewModel = moviesViewModel,
                    movieId = it
                )
            }
        }
        composable(NavScreen.About.route) {
            About(navController)
        }
    }
}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("home")

    object MovieDetails : NavScreen("movies") {
        const val routeWithArgument: String = "movies/{movieId}"
        const val argMovieId: String = "movieId"
    }

    object About : NavScreen("about")
}
