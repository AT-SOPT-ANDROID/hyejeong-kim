package org.sopt.at.presentation.ui.main

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.core.component.bottombar.TvingBottomBar
import org.sopt.at.core.component.dialog.HistoryAddDialog
import org.sopt.at.core.component.dialog.HistoryDeleteDialog
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
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var showAddDialog by remember { mutableStateOf(false) }

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
                        showAddDialog = true
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

    // history 시리즈 다이얼로그 표시
    if (showAddDialog) {
        HistoryDialog (
            isAddDialog = true,
            onAddClick = {
                showAddDialog = false
                historyViewModel.insertSeries()
            },
            onDismissRequest = {
                showAddDialog = false
            }
        )
    }

    val selectedSeries by historyViewModel.selectedSeries.collectAsState()
    val showDeleteDialog by historyViewModel.showDeleteDialog.collectAsState()

    // history 시리즈 삭제 다이얼로그 표시
    if (showDeleteDialog) {
        HistoryDialog (
            isAddDialog = false,
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