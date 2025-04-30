package org.sopt.at.core.component.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.data.local.Series

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistorySeriesItem(
    modifier: Modifier = Modifier,
    series: Series,
    onLongClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .combinedClickable(
                onClick = {
                },
                onLongClick = onLongClick
            )
            .background(Color.Black)
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.poster1),
            contentDescription = "series image",
            modifier = Modifier
                .size(56.dp)
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.size(12.dp))

        Text(
            text = "${series.title} ${series.id}",
            color = Color.White,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
private fun PreviewHistorySeriesItem() {
    HistorySeriesItem(
        series = Series(
            title = "",
            imageUrl = ""
        )
    )
}