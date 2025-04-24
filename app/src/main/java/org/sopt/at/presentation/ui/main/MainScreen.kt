package org.sopt.at.presentation.ui.main

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.core.component.bottombar.TvingBottomBar
import org.sopt.at.core.component.dialog.HistoryAddDialog
import org.sopt.at.core.component.dialog.HistoryDeleteDialog
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
    historyViewModel: HistoryViewModel = hiltViewModel()
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
                        viewModel.showAddDialog()
                    }
                )
            }
        }
    ) { innerPadding ->
        NavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            historyViewModel = historyViewModel
        )
    }

    val showAddDialog by viewModel.showAddDialog.collectAsState()

    // history 시리즈 추가 다이얼로그 표시
    if (showAddDialog) {
        HistoryAddDialog(
            onAddClick = {
                viewModel.dismissAddDialog()
                historyViewModel.insertSeries()
            },
            onDismissRequest = {
                viewModel.dismissAddDialog()
            }
        )
    }

    val showDeleteDialog by historyViewModel.showDeleteDialog.collectAsState()
    val selectedSeries by historyViewModel.selectedSeries.collectAsState()

    // history 시리즈 삭제 다이얼로그 표시
    if (showDeleteDialog) {
        Log.d("selectedSeries", selectedSeries.toString())
        HistoryDeleteDialog(
            onDeleteClick = {
                historyViewModel.dismissDeleteDialog()
                historyViewModel.deleteSeries(selectedSeries)
            },
            onDismissRequest = {
                historyViewModel.dismissDeleteDialog()
            }
        )
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    MainScreen()
}