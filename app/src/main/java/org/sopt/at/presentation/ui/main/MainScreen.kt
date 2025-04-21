package org.sopt.at.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.core.component.bottombar.BottomNavItem
import org.sopt.at.core.component.bottombar.TvingBottomBar
import org.sopt.at.core.component.bottombar.navigation.BottomNavGraph
import org.sopt.at.core.component.topbar.TvingHomeTopBar

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == BottomNavItem.Home.route) {
                TvingHomeTopBar()
            }
        },
        bottomBar = {
            TvingBottomBar(navController = navController)
        }
    ) { innerPadding ->
        BottomNavGraph(
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