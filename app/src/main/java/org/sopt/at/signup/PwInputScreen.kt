package org.sopt.at.signup

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.PasswordTextField
import org.sopt.at.SignInActivity

@Composable
fun PwInputScreen(
    pw: String,
    onPwChange: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showPassword by remember { mutableStateOf(value = false) }

    // pw 유효성 검사: 영문, 숫자, 특수문자(~!@#$%^&*) 조합 8~15자리
    val isValidPw = pw.matches(Regex("^[a-zA-z0-9~!@#$%^&*]{8,15}$"))
    val context = LocalContext.current

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
                Button(
                    onClick = {
                        // pw가 유효할 경우 로그인 뷰로 이동
                        if (isValidPw) {
                            val intent = Intent(context, SignInActivity::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            context.startActivity(intent)
                        } else {
                            // pw가 유효하지 않을 경우 스낵바
                            scope.launch {
                                snackbarHostState.showSnackbar("비밀번호가 유효하지 않습니다.")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.LightGray
                    ),
                ) {
                    Text(
                        "다음",
                        fontSize = 16.sp
                    )
                }

            }

        }
    }
}