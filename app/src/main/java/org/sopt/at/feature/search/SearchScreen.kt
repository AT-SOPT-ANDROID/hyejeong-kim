package org.sopt.at.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.core.component.textfield.IdTextField
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {

    var keyword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TvingTheme.colors.BasicBlack)
            .padding(15.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IdTextField(
            id = keyword,
            onIdChange = { keyword = it },
            placeholderText = stringResource(R.string.search_keyword_input),
            modifier = Modifier
                .padding(top = 20.dp)
                .border(
                    width = 1.dp,
                    color = TvingTheme.colors.Gray2,
                    shape = RoundedCornerShape(5.dp)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchScreen() {
    SearchScreen()
}