package org.sopt.at.presentation.ui.main.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.at.R
import org.sopt.at.presentation.ui.main.history.content.HistoryContent

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val seriesList by viewModel.seriesList.collectAsState()

    // UI, ViewModel 분리
    HistoryContent(seriesList = seriesList)
}

@Preview(showBackground = true)
@Composable
private fun PreviewHistoryScreen() {
    HistoryScreen()
}