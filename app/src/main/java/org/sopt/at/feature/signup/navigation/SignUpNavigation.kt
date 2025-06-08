package org.sopt.at.feature.signup.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.at.core.navigation.Route
import org.sopt.at.feature.signup.SignUpRoute

fun NavController.navigateToSignUp(navOptions: NavOptions) {
    navigate(Route.SignUp, navOptions)
}

fun NavGraphBuilder.signUpNavGraph(
    navigateToSignIn: () -> Unit,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    composable<Route.SignUp> {
        SignUpRoute(
            navigateToSignIn = navigateToSignIn,
            showSnackbar = showSnackbar,
            modifier = modifier
        )
    }
}