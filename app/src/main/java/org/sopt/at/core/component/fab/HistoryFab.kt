package org.sopt.at.core.component.fab

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.R

@Composable
fun HistoryFab(
    modifier: Modifier = Modifier,
    onFabClick: () -> Unit = {}
) {
    SmallFloatingActionButton(
        onClick = onFabClick,
        containerColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.history_dialog_add)
        )
    }
}

@Preview
@Composable
private fun PreviewHistoryFab() {
    HistoryFab()
}