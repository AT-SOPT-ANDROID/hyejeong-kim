package org.sopt.at.feature.editnickname.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.at.core.navigation.Route
import org.sopt.at.feature.editnickname.EditNicknameRoute

fun NavController.navigateToEditNickname(navOptions: NavOptions) {
    navigate(Route.EditNickname, navOptions)
}

fun NavGraphBuilder.editNicknameNavGraph(
    navigateToMy: () -> Unit,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    composable<Route.EditNickname> {
        EditNicknameRoute(
            navigateToMy = navigateToMy,
            showSnackbar = showSnackbar,
            modifier = modifier
        )
    }
}