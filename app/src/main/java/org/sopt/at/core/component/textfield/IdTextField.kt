package org.sopt.at.core.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.sopt.at.R
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun IdTextField(
    id: String,
    onIdChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = id,
        onValueChange = onIdChange,
        placeholder = {
            Text(
                text = stringResource(R.string.textfield_id),
                style = TvingTheme.typography.body
            )
        },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = TvingTheme.colors.Gray4,
            unfocusedContainerColor = TvingTheme.colors.Gray4,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = TvingTheme.colors.BasicWhite,
            focusedPlaceholderColor = TvingTheme.colors.Gray2,
            unfocusedPlaceholderColor = TvingTheme.colors.Gray2
        ),
        textStyle = TextStyle(
            color = TvingTheme.colors.BasicWhite,
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
    )
}