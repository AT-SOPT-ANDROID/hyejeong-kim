package org.sopt.at.feature.editnickname

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.at.R
import org.sopt.at.core.component.button.TvingButton
import org.sopt.at.core.component.textfield.BasicTextField
import org.sopt.at.data.model.request.EditNicknameRequest
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun EditNicknameRoute(
    navigateToMy: () -> Unit,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    EditNicknameScreen(
        navigateToMy = navigateToMy,
        showSnackbar = showSnackbar,
        modifier = modifier
    )
}

@Composable
fun EditNicknameScreen(
    navigateToMy: () -> Unit,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditNicknameViewModel = hiltViewModel()
) {
    var nickname by remember { mutableStateOf("") }

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect {
            when (it) {
                EditNicknameEffect.NavigateToMy -> navigateToMy()
                is EditNicknameEffect.ShowSnackbar -> showSnackbar(it.message)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = TvingTheme.colors.BasicBlack)
            .padding(15.dp)
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BasicTextField(
            value = nickname,
            onValueChange = {
                nickname = it
            },
            placeholderText = stringResource(R.string.edit_nickname_input),
            modifier = Modifier
                .padding(top = 20.dp)
                .border(
                    width = 1.dp,
                    color = TvingTheme.colors.Gray2,
                    shape = RoundedCornerShape(5.dp)
                )
        )
        // 닉네임 변경 버튼
        TvingButton(
            onClick = {
                viewModel.sendEvent(
                    EditNicknameEvent.PatchNickname(
                        request = EditNicknameRequest(
                            nickname
                        )
                    )
                )
            },
            content = stringResource(R.string.button_edit_nickname)
        )
    }

}

@Preview
@Composable
private fun PreviewEditNicknameScreen() {
    EditNicknameScreen(
        navigateToMy = {},
        showSnackbar = {}
    )
}