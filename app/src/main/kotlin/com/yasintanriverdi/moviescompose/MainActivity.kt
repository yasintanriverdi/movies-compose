package com.yasintanriverdi.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import com.yasintanriverdi.moviescompose.ui.main.Main
import com.yasintanriverdi.moviescompose.ui.movies.MoviesViewModel
import com.yasintanriverdi.moviescompose.ui.theme.MoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesTheme {
                Main(moviesViewModel)
            }
        }
    }
}
