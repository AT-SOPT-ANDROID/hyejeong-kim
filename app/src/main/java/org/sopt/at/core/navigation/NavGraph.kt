package org.sopt.at.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.feature.editnickname.EditNicknameScreen
import org.sopt.at.feature.history.HistoryScreen
import org.sopt.at.feature.history.HistoryViewModel
import org.sopt.at.feature.home.HomeScreen
import org.sopt.at.feature.live.LiveScreen
import org.sopt.at.feature.my.MyScreen
import org.sopt.at.feature.search.SearchScreen
import org.sopt.at.feature.shorts.ShortsScreen
import org.sopt.at.feature.signin.SignInScreen
import org.sopt.at.feature.signup.SignUpScreen
import org.sopt.at.feature.splash.SplashScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    historyViewModel: HistoryViewModel,
    showSnackbar: (String) -> Unit = {}
) {
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
                navigateToEditNickname = {
                    navController.navigate(NavRoute.EditNickname.route)
                },
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
        composable(route = NavRoute.EditNickname.route) {
            EditNicknameScreen(
                navigateToMy = {
                    navController.navigate(NavRoute.My.route) {
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                showSnackbar = showSnackbar
            )
        }
    }
}