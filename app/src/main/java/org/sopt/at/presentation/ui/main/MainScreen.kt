package org.sopt.at.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.core.component.bottombar.TvingBottomBar
import org.sopt.at.core.component.dialog.HistoryDialog
import org.sopt.at.core.component.fab.HistoryFab
import org.sopt.at.core.component.topbar.TvingHomeTopBar
import org.sopt.at.core.component.topbar.TvingTopBar
import org.sopt.at.core.navigation.BottomNavRoute
import org.sopt.at.core.navigation.NavGraph
import org.sopt.at.core.navigation.NavRoute
import org.sopt.at.presentation.ui.main.history.HistoryViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
    historyViewModel: HistoryViewModel = viewModel()
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
        },
        floatingActionButton = {
            if (currentRoute == BottomNavRoute.History.route) {
                HistoryFab(
                    onFabClick = {
                        viewModel.showDialog()
                    }
                )
            }
        }
    ) { innerPadding ->
        NavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }

    val showDialog by viewModel.showDialog.collectAsState()

    // 다이얼로그 표시
    if (showDialog) {
        HistoryDialog(
            onAddClick = {
                // 추가 로직 처리
                viewModel.dismissDialog()
                historyViewModel.insertSeries()
            },
            onDismissRequest = {
                viewModel.dismissDialog()
            }
        )
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    MainScreen()
}