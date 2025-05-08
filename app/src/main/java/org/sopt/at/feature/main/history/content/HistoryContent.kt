package org.sopt.at.feature.main.history.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.sopt.at.R
import org.sopt.at.core.component.list.HistorySeriesItem
import org.sopt.at.data.local.entity.Series
import org.sopt.at.feature.main.history.HistoryViewModel
import org.sopt.at.ui.theme.TvingTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    seriesList: ImmutableList<Series>,
    showDeleteDialog: (Series) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.history_series_list),
            color = TvingTheme.colors.BasicWhite,
            style = TvingTheme.typography.title
        )
        LazyColumn {
            itemsIndexed(seriesList) { index, series ->
                HistorySeriesItem(
                    onLongClick = {
                        showDeleteDialog(series)
                    },
                    series = series
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryContent() {
    val viewModel: HistoryViewModel = hiltViewModel()
    HistoryContent(
        seriesList = persistentListOf(),
        showDeleteDialog = viewModel::showDeleteDialog
    )
}
