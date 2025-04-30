package org.sopt.at.core.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.sopt.at.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryDialog(
    isAddDialog: Boolean,
    onAddClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(12.dp),
            color = Color.White
        ) {
            Column {
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = stringResource(if (isAddDialog) R.string.history_dialog_add_series else R.string.history_dialog_delte_series),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .padding(vertical = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        if (isAddDialog) onAddClick() else onDeleteClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Text(
                        text = stringResource(if (isAddDialog) R.string.history_dialog_add else R.string.history_dialog_delete),
                        fontSize = 16.sp
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryDialog() {
    HistoryDialog(
        isAddDialog = true,
        onAddClick = {},
        onDeleteClick = {},
        onDismissRequest = {}
    )
}