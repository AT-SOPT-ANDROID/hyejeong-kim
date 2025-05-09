package org.sopt.at.feature.live

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun LiveRoute(modifier: Modifier = Modifier) {
    LiveScreen(
        modifier = modifier
    )
}

@Composable
fun LiveScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "라이브 화면",
            color = TvingTheme.colors.BasicWhite
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLiveScreen() {
    LiveScreen()
}