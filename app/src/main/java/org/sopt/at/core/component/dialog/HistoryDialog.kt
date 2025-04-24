package org.sopt.at.core.component.dialog

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryDialog(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onAddClick()
                }
            ) {
                Text(
                    text = stringResource(R.string.history_dialog_add)
                )
            }
        },
        modifier = Modifier.background(
            color = Color.White
        ),
        title = {
            Text(
                text = stringResource(R.string.history_dialog_add_series)
            )
        },
        text = {
            Text(
                text = stringResource(R.string.history_dialog_add_series_question)
            )
        }
    )
}

@Preview
@Composable
private fun PreviewHistoryDialog() {
    HistoryDialog()
}