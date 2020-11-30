package com.yasintanriverdi.moviescompose.ui.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.yasintanriverdi.moviescompose.R
import com.yasintanriverdi.moviescompose.ui.layout.LinkifyText
import com.yasintanriverdi.moviescompose.ui.layout.NavigateBackAppBar
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun About(navController: NavController) {
    Scaffold(
        topBar = { AboutAppBar(backClick = { navController.navigateUp() }) },
        modifier = Modifier.statusBarsPadding()
    ) {
        val modifier = Modifier.padding(it)
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.developer_name),
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold
            )

            LinkifyText(R.string.github, R.string.url_github)
            LinkifyText(R.string.linkedIn, R.string.url_linkedin)
        }
    }
}

@Composable
fun AboutAppBar(backClick: () -> Unit) {
    NavigateBackAppBar(
        title = {
            Text(
                text = stringResource(R.string.about),
                fontWeight = FontWeight.Bold
            )
        },
        backClick = backClick,
    )
}
