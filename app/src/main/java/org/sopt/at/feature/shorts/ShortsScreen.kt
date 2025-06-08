package org.sopt.at.feature.shorts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun ShortsRoute(modifier: Modifier = Modifier) {
    ShortsScreen(
        modifier = Modifier
    )
}

@Composable
fun ShortsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "숏츠 화면",
            color = TvingTheme.colors.BasicWhite
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewShortsScreen() {
    ShortsScreen()
}