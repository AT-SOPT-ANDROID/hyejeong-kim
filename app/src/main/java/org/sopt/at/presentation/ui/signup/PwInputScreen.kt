package org.sopt.at.presentation.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.core.component.button.TvingButton
import org.sopt.at.core.component.textfield.PasswordTextField
import org.sopt.at.core.component.topbar.TvingTopBar

@Composable
fun PwInputScreen(
    modifier: Modifier = Modifier,
    pw: String,
    onPwChange: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    var showPassword by remember { mutableStateOf(value = false) }

    // pw 유효성 검사: 영문, 숫자, 특수문자(~!@#$%^&*) 조합 8~15자리
    val isValidPw =
        pw.matches(Regex("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#\$%^&*])[a-zA-Z0-9~!@#\$%^&*]{8,15}$"))

    // 스낵바
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TvingTopBar(
                onBackClick = onBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .background(color = Color.Black)
                .padding(innerPadding)
                .padding(15.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // 비밀번호 입력
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.sign_up_pw_input),
                        color = Color.White,
                        fontSize = 20.sp
                    )

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
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }

                // 다음 버튼
                TvingButton(
                    onClick = {
                        // pw가 유효할 경우 로그인 뷰로 이동
                        if (isValidPw) {
                            onNext()
                        } else {
                            // pw가 유효하지 않을 경우 스낵바
                            scope.launch {
                                snackbarHostState.showSnackbar("비밀번호가 유효하지 않습니다.")
                            }
                        }
                    },
                    content = stringResource(R.string.button_next)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPwInputScreen() {
    PwInputScreen(
        pw = "",
        onPwChange = {},
        onNext = {},
        onBack = {}
    )
}