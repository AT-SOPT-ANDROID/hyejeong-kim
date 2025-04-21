package org.sopt.at.core.component.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.R

@Composable
fun TvingHomeTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.tving_logo),
            contentDescription = "TVING LOGO",
            modifier = Modifier.padding(0.dp).size(width = 80.dp, height = 32.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.tving_square_logo),
            contentDescription = "TVING SQUARE LOGO",
            modifier = Modifier.padding(0.dp).size(24.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewTvingHomeTopBar() {
    TvingHomeTopBar()
}