package com.yasintanriverdi.moviescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.yasintanriverdi.moviescompose.ui.main.Main
import com.yasintanriverdi.moviescompose.ui.movies.MoviesViewModel
import com.yasintanriverdi.moviescompose.ui.theme.MoviesTheme
import com.yasintanriverdi.moviescompose.ui.utils.LocalSysUiController
import com.yasintanriverdi.moviescompose.ui.utils.SystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This app draws behind the system bars, so we want to handle fitting system windows
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = remember { SystemUiController(window) }
            CompositionLocalProvider(LocalSysUiController provides systemUiController) {
                ProvideWindowInsets {
                    MoviesTheme {
                        Main(moviesViewModel)
                    }
                }
            }
        }
    }
}
