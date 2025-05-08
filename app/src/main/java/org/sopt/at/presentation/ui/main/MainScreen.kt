package org.sopt.at.presentation.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch
import org.sopt.at.core.component.bottombar.TvingBottomBar
import org.sopt.at.core.component.dialog.HistoryDialog
import org.sopt.at.core.component.fab.HistoryFab
import org.sopt.at.core.component.topbar.TvingHomeTopBar
import org.sopt.at.core.component.topbar.TvingTopBar
import org.sopt.at.core.navigation.BottomNavRoute
import org.sopt.at.core.navigation.LocalNavController
import org.sopt.at.core.navigation.NavGraph
import org.sopt.at.core.navigation.NavRoute
import org.sopt.at.presentation.ui.main.history.HistoryViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var showAddDialog by remember { mutableStateOf(false) }

    // 스낵바
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val showSnackbar: (String) -> Unit = { message ->
        scope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        topBar = {
            if (currentRoute == BottomNavRoute.Home.route) {
                TvingHomeTopBar(
                    navigateToMy = { navController.navigate(route = NavRoute.My.route) }
                )
            } else if (currentRoute == NavRoute.My.route) {
                TvingTopBar(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.padding(15.dp)
                )
            }
        },
        bottomBar = {
            if (currentRoute != NavRoute.SignUp.route && currentRoute != NavRoute.SignIn.route) {
                TvingBottomBar()
            }
        },
        floatingActionButton = {
            if (currentRoute == BottomNavRoute.History.route) {
                HistoryFab(
                    onFabClick = {
                        showAddDialog = true
                    }
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        NavGraph(
            modifier = Modifier.padding(innerPadding),
            historyViewModel = historyViewModel,
            showSnackbar = showSnackbar
        )
    }

    // history 시리즈 다이얼로그 표시
    if (showAddDialog) {
        HistoryDialog(
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
        HistoryDialog(
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