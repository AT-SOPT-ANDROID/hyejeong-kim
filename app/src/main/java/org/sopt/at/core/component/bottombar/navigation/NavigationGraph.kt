package org.sopt.at.core.component.bottombar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.presentation.ui.main.history.HistoryScreen
import org.sopt.at.presentation.ui.main.home.HomeScreen
import org.sopt.at.presentation.ui.main.live.LiveScreen
import org.sopt.at.presentation.ui.main.search.SearchScreen
import org.sopt.at.presentation.ui.main.shorts.ShortsScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen()
        }
        composable(route = "shorts") {
            ShortsScreen()
        }
        composable(route = "live") {
            LiveScreen()
        }
        composable(route = "search") {
            SearchScreen()
        }
        composable(route = "history") {
            HistoryScreen()
        }
    }
}