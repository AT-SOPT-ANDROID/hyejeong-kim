package org.sopt.at.presentation.ui.main.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.at.presentation.ui.main.history.content.HistoryContent

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel
) {
    val seriesList by viewModel.seriesList.collectAsState()

    // UI, ViewModel 분리
    HistoryContent(
        seriesList = seriesList,
        viewModel = viewModel
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewHistoryScreen() {
    val viewModel: HistoryViewModel = hiltViewModel()
    HistoryScreen(viewModel = viewModel)
}