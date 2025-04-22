package org.sopt.at.core.navigation

sealed class NavRoute(
    val route: String
) {
    data object My : NavRoute(
        route = "my"
    )
}