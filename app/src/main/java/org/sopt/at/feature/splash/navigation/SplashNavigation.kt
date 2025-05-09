package org.sopt.at.feature.splash.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.at.core.navigation.Route
import org.sopt.at.feature.splash.SplashRoute

fun NavController.navigateToSplash(navOptions: NavOptions) {
    navigate(Route.Splash, navOptions)
}

fun NavGraphBuilder.splashNavGraph(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    composable<Route.Splash> {
        SplashRoute(
            navigateToHome = navigateToHome,
            navigateToSignIn = navigateToSignIn,
            modifier = modifier
        )
    }
}