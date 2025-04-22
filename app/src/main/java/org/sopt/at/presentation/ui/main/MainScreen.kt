package org.sopt.at.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.core.component.bottombar.TvingBottomBar
import org.sopt.at.core.component.topbar.TvingHomeTopBar
import org.sopt.at.core.component.topbar.TvingTopBar
import org.sopt.at.core.navigation.BottomNavRoute
import org.sopt.at.core.navigation.NavGraph
import org.sopt.at.core.navigation.NavRoute

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == BottomNavRoute.Home.route) {
                TvingHomeTopBar(
                    navController = navController
                )
            } else if (currentRoute == NavRoute.My.route) {
                TvingTopBar(
                    onBackClick = {
                        navController.navigate(route = BottomNavRoute.Home.route)
                    }
                )
            }
        },
        bottomBar = {
            TvingBottomBar(navController = navController)
        }
    ) { innerPadding ->
        NavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    MainScreen()
}