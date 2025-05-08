package org.sopt.at.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.at.R
import org.sopt.at.core.component.textfield.BasicTextField
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val nicknameList by viewModel.nicknameList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TvingTheme.colors.BasicBlack)
            .padding(15.dp)
            .imePadding(),

        ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                viewModel.sendEvent(SearchEvent.OnQueryChanged(it))
            },
            placeholderText = stringResource(R.string.search_keyword_input),
            modifier = Modifier
                .padding(top = 20.dp)
                .border(
                    width = 1.dp,
                    color = TvingTheme.colors.Gray2,
                    shape = RoundedCornerShape(5.dp)
                )
        )

        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(nicknameList) { nickname ->
                Text(
                    text = nickname,
                    color = TvingTheme.colors.BasicWhite,
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchScreen() {
    SearchScreen()
}