package org.sopt.at.feature.my.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.at.core.navigation.Route
import org.sopt.at.feature.my.MyRoute

fun NavController.navigateToMy(navOptions: NavOptions) {
    navigate(Route.My, navOptions)
}

fun NavGraphBuilder.myNavGraph(
    navigateToSignIn: () -> Unit,
    navigateToEditNickname: () -> Unit,
    modifier: Modifier = Modifier
) {
    composable<Route.My> {
        MyRoute(
            navigateToSignIn = navigateToSignIn,
            navigateToEditNickname = navigateToEditNickname,
            modifier = modifier
        )
    }
}