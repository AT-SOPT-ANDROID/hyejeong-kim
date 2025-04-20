package org.sopt.at.presentation.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.presentation.ui.signin.PasswordTextField

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
    val isValidPw = pw.matches(Regex("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#\$%^&*])[a-zA-Z0-9~!@#\$%^&*]{8,15}$"))

    // 스낵바
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
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
            // 뒤로 가기 버튼
            Box(
                modifier = Modifier.size(24.dp)
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "뒤로가기",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.White
                    )
                }
            }

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
                        text = "비밀번호를 입력해주세요.",
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
                            text = "영문, 숫자, 특수문자(~!@#$%^) 조합 8~15자리",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }

                // 다음 버튼
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp)
                        .background(Color.Black, RoundedCornerShape(10.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                        .clickable(
                            // 리플 효과 제거
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
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
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "다음",
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                }

            }

        }
    }
}