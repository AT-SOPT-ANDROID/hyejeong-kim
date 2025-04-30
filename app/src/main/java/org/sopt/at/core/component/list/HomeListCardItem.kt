package org.sopt.at.core.component.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeListCardItem(
    item: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = item),
        contentDescription = "CardItem",
        modifier = modifier.clip(RoundedCornerShape(5.dp)),
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
private fun PreviewHomeListCardItem() {
    HomeListCardItem(item = 0)
}