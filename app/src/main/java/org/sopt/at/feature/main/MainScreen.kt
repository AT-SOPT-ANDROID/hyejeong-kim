package org.sopt.at.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import org.sopt.at.core.component.bottombar.TvingBottomBar
import org.sopt.at.core.component.dialog.HistoryDialog
import org.sopt.at.core.component.fab.HistoryFab
import org.sopt.at.core.component.topbar.TvingHomeTopBar
import org.sopt.at.core.component.topbar.TvingTopBar
import org.sopt.at.feature.history.HistoryViewModel
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
    // 스낵바
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val showSnackbar: (String) -> Unit = { message ->
        scope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    MainScreenContent(
        navigator = navigator,
        snackbarHostState = snackbarHostState,
        historyViewModel = historyViewModel,
        showSnackbar = showSnackbar
    )
}

@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    snackbarHostState: SnackbarHostState,
    historyViewModel: HistoryViewModel,
    showSnackbar: (String) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            if (navigator.showHomeTopBar()) {
                TvingHomeTopBar(
                    navigateToMy = {
                        navigator.navigateToMy()
                    }
                )
            } else if (
                navigator.showTopBar()
            ) {
                TvingTopBar(
                    onBackClick = {
                        navigator.popBackStack()
                    }
                )
            }
        },
        content = { innerPadding ->
            MainNavHost(
                navigator = navigator,
                modifier = Modifier
                    .fillMaxSize()
                    .background(TvingTheme.colors.BasicBlack)
                    .padding(innerPadding),
                historyViewModel = historyViewModel,
                showSnackbar = showSnackbar
            )
        },
        bottomBar = {
            TvingBottomBar(
                modifier = Modifier
                    .background(TvingTheme.colors.BasicBlack)
                    .navigationBarsPadding(),
                visible = navigator.showBottomBar(),
                tabs = MainTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        },
        floatingActionButton = {
            if (navigator.showHistoryFab()) {
                HistoryFab(
                    onFabClick = {
                        showAddDialog = true
                    }
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    )
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

    val selectedSeries by historyViewModel.selectedSeries.collectAsStateWithLifecycle()
    val showDeleteDialog by historyViewModel.showDeleteDialog.collectAsStateWithLifecycle()

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