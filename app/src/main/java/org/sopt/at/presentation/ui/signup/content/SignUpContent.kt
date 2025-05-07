package org.sopt.at.presentation.ui.signup.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.core.component.button.TvingButton
import org.sopt.at.core.component.textfield.IdTextField
import org.sopt.at.core.component.textfield.PasswordTextField
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun SignUpContent(
    step: Int,
    id: String = "",
    onIdChange: (String) -> Unit = {},
    pw: String = "",
    onPwChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    onNext: () -> Unit,
    onBack: () -> Unit,
    showErrorSnackbar: (String) -> Unit
) {
    var showPassword by remember { mutableStateOf(value = false) }

    val isIdStep = step == 1

    val isValid = if (isIdStep) {
        // id 유효성 검사: 영문 소문자 또는 영문 소문자, 숫자 조합 6~12자리
        id.matches(Regex("^(?=.*[a-z])[a-z0-9]{6,12}$"))
    } else {
        // pw 유효성 검사: 영문, 숫자, 특수문자(~!@#$%^&*) 조합 8~15자리
        pw.matches(Regex("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#\$%^&*])[a-zA-Z0-9~!@#\$%^&*]{8,15}$"))
    }

    val invalidIdMsg = stringResource(R.string.sign_up_invalid_id)
    val invalidPwMsg = stringResource(R.string.sign_up_invalid_pw)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = TvingTheme.colors.BasicBlack)
            .padding(15.dp),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp)
                .imePadding(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 제목
                Text(
                    text = if (isIdStep) stringResource(
                        R.string.sign_up_id_input
                    ) else
                        stringResource(
                            R.string.sign_up_pw_input
                        ),
                    color = TvingTheme.colors.BasicWhite,
                    style = TvingTheme.typography.subtitle
                )

                when (step) {
                    1 -> {
                        // 아이디 입력 창
                        Column {
                            IdTextField(
                                id = id,
                                onIdChange = onIdChange,
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .border(
                                        width = 1.dp,
                                        color = TvingTheme.colors.Gray2,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                            )

                            Text(
                                text = stringResource(R.string.sign_up_id_condition),
                                color = TvingTheme.colors.Gray2,
                                style = TvingTheme.typography.caption
                            )
                        }

                    }

                    else -> {
                        // 비밀번호 입력 창
                        Column {
                            PasswordTextField(
                                password = pw,
                                onPasswordChange = onPwChange,
                                showPassword = showPassword,
                                onTogglePasswordVisibility = { showPassword = !showPassword },
                                modifier = Modifier
                                    .padding(top = 20.dp)
                                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(5.dp)),
                            )

                            Text(
                                text = stringResource(R.string.sign_up_pw_condition),
                                color = TvingTheme.colors.Gray2,
                                style = TvingTheme.typography.caption
                            )
                        }
                    }
                }

            }

            // 다음 버튼
            TvingButton(
                onClick = {
                    // id가 유효할 경우 회원가입 비밀번호 뷰로 이동
                    if (isValid) {
                        onNext()
                    } else {
                        // id가 유효 하지 않을 시 스낵바
                        val message = if (isIdStep) invalidIdMsg else invalidPwMsg

                        showErrorSnackbar(message)
                    }
                },
                content = stringResource(R.string.button_next)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewSignUpContent() {
    SignUpContent(
        step = 1,
        id = "",
        onIdChange = {},
        pw = "",
        onPwChange = {},
        onNext = {},
        onBack = {},
        showErrorSnackbar = {}
    )
}