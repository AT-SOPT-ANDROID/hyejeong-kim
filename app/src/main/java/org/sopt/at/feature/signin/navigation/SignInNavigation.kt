package org.sopt.at.feature.signin.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.at.core.navigation.Route
import org.sopt.at.feature.signin.SignInRoute

fun NavController.navigateToSignIn(navOptions: NavOptions) {
    navigate(Route.SignIn, navOptions)
}

fun NavGraphBuilder.signInNavGraph(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    composable<Route.SignIn> {
        SignInRoute(
            navigateToSignUp = navigateToSignUp,
            navigateToHome = navigateToHome,
            showSnackbar = showSnackbar,
            modifier = modifier
        )
    }
}