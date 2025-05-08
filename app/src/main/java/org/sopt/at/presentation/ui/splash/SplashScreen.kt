package org.sopt.at.presentation.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.at.R


@Composable
fun SplashScreen(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        when (isLoggedIn) {
            true -> navigateToHome()
            false -> navigateToSignIn()
            else -> Unit
        }
    }

    Box(
        modifier = modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.tving_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(0.5f)
        )
    }
}

@Preview
@Composable
private fun PreviewSplashScreen() {
    SplashScreen(
        navigateToHome = {},
        navigateToSignIn = {}
    )
}