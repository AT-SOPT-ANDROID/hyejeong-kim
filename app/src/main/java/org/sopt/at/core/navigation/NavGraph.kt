package org.sopt.at.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.presentation.ui.main.history.HistoryScreen
import org.sopt.at.presentation.ui.main.history.HistoryViewModel
import org.sopt.at.presentation.ui.main.home.HomeScreen
import org.sopt.at.presentation.ui.main.live.LiveScreen
import org.sopt.at.presentation.ui.main.my.MyScreen
import org.sopt.at.presentation.ui.main.search.SearchScreen
import org.sopt.at.presentation.ui.main.shorts.ShortsScreen
import org.sopt.at.presentation.ui.signin.SignInScreen
import org.sopt.at.presentation.ui.signup.SignUpScreen
import org.sopt.at.presentation.ui.splash.SplashScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    historyViewModel: HistoryViewModel,
    showSnackbar: (String) -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash.route,
        modifier = modifier
    ) {
        composable(route = NavRoute.Splash.route) {
            SplashScreen(
                navigateToHome = {
                    navController.navigate(BottomNavRoute.Home.route) {
                        popUpTo(NavRoute.Splash.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignIn = {
                    navController.navigate(NavRoute.SignIn.route) {
                        popUpTo(NavRoute.Splash.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = NavRoute.SignIn.route) {
            SignInScreen(
                navigateToHome = {
                    navController.navigate(BottomNavRoute.Home.route) {
                        popUpTo(NavRoute.SignIn.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToSignUp = {
                    navController.navigate(NavRoute.SignUp.route)
                },
                showSnackbar = showSnackbar
            )
        }
        composable(route = NavRoute.SignUp.route) {
            SignUpScreen(
                navigateToSignIn = {
                    navController.popBackStack()
                },
                showSnackbar = showSnackbar
            )
        }
        composable(route = BottomNavRoute.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNavRoute.Shorts.route) {
            ShortsScreen()
        }
        composable(route = BottomNavRoute.Live.route) {
            LiveScreen()
        }
        composable(route = BottomNavRoute.Search.route) {
            SearchScreen()
        }
        composable(route = BottomNavRoute.History.route) {
            HistoryScreen(viewModel = historyViewModel)
        }
        composable(route = NavRoute.My.route) {
            MyScreen(
                navigateToSignIn = {
                    navController.navigate(NavRoute.SignIn.route) {
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}