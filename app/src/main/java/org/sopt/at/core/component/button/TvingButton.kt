package org.sopt.at.core.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun TvingButton(
    modifier: Modifier = Modifier,
    content: String = "",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(50.dp)
            .background(
                color = TvingTheme.colors.BasicBlack,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                width = 1.dp,
                color = TvingTheme.colors.Gray2,
                shape = RoundedCornerShape(10.dp)
            )
            .noRippleClickable(onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = content,
            color = TvingTheme.colors.Gray1,
            style = TvingTheme.typography.body
        )
    }
}
