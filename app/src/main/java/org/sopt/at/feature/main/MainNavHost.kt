package org.sopt.at.feature.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.at.feature.editnickname.navigation.editNicknameNavGraph
import org.sopt.at.feature.history.HistoryViewModel
import org.sopt.at.feature.history.navigation.historyNavGraph
import org.sopt.at.feature.home.navigation.homeNavGraph
import org.sopt.at.feature.live.navigation.liveNavGraph
import org.sopt.at.feature.my.navigation.myNavGraph
import org.sopt.at.feature.search.navigation.searchNavGraph
import org.sopt.at.feature.shorts.navigation.shortsNavGraph
import org.sopt.at.feature.signin.navigation.signInNavGraph
import org.sopt.at.feature.signup.navigation.signUpNavGraph
import org.sopt.at.feature.splash.navigation.splashNavGraph
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    historyViewModel: HistoryViewModel,
    showSnackbar: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(TvingTheme.colors.BasicBlack)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
            enterTransition = {
                EnterTransition.None
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            homeNavGraph()
            liveNavGraph()
            shortsNavGraph()
            searchNavGraph()
            historyNavGraph(
                historyViewModel = historyViewModel
            )
            myNavGraph(
                navigateToSignIn = {
                    navigator.navigateToSignIn()
                },
                navigateToEditNickname = {
                    navigator.navigateToEditNickname()
                }
            )
            signInNavGraph(
                navigateToSignUp = {
                    navigator.navigateToSignUp()
                },
                navigateToHome = {
                    navigator.navigateToHome()
                },
                showSnackbar = showSnackbar
            )
            signUpNavGraph(
                navigateToSignIn = {
                    navigator.navigateToSignIn()
                },
                showSnackbar = showSnackbar
            )
            splashNavGraph(
                navigateToSignIn = {
                    navigator.navigateToSignIn()
                },
                navigateToHome = {
                    navigator.navigateToHome()
                }
            )
            editNicknameNavGraph(
                navigateToMy = {
                    navigator.navigateToMy()
                },
                showSnackbar = showSnackbar
            )
        }
    }
}