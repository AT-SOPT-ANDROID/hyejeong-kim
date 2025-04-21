package org.sopt.at.core.component.bottombar.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.core.component.bottombar.BottomNavItem
import org.sopt.at.presentation.ui.main.history.HistoryScreen
import org.sopt.at.presentation.ui.main.home.HomeScreen
import org.sopt.at.presentation.ui.main.live.LiveScreen
import org.sopt.at.presentation.ui.main.search.SearchScreen
import org.sopt.at.presentation.ui.main.shorts.ShortsScreen

@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(route = BottomNavItem.Home.route) {
            HomeScreen()
        }
        composable(route = BottomNavItem.Shorts.route) {
            ShortsScreen()
        }
        composable(route = BottomNavItem.Live.route) {
            LiveScreen()
        }
        composable(route = BottomNavItem.Search.route) {
            SearchScreen()
        }
        composable(route = BottomNavItem.History.route) {
            HistoryScreen()
        }
    }
}