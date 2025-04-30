package org.sopt.at.core.navigation

sealed class NavRoute(
    val route: String
) {
    data object SignIn : NavRoute(
        route = "signIn"
    )
    data object SignUp : NavRoute(
        route = "signUp"
    )
    data object My : NavRoute(
        route = "my"
    )
}