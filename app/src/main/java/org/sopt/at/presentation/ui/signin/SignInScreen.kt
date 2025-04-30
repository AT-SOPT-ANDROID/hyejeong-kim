package org.sopt.at.presentation.ui.signin

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.R
import org.sopt.at.core.component.textfield.IdTextField
import org.sopt.at.core.component.textfield.PasswordTextField
import org.sopt.at.core.util.AutoLogin
import org.sopt.at.core.util.IntentKeys
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.presentation.ui.main.MainActivity

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    signUpId: String = "",
    signUpPw: String = "",
    navigateToSignUp: () -> Unit = {}
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }
    val context = LocalContext.current

    // 스낵바
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val autoLogin = AutoLogin(context)

    // 로그인 버튼 색깔
    val isFormFilled = id.isNotBlank() && password.isNotBlank()
    val loginButtonColor = if (isFormFilled) Color(0xFFFF143C) else Color(0xFF404040)
    val loginTextColor = if (isFormFilled) Color.White else Color.Gray

    val isValidProfile =
        id == signUpId && password == signUpPw && signUpId.isNotBlank() && signUpPw.isNotBlank()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(innerPadding)
                .padding(top = 100.dp)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_in_title),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // 아이디 입력 창
            IdTextField(
                id = id,
                onIdChange = { id = it },
                modifier = Modifier
                    .padding(top = 20.dp)
            )

            // 비밀번호 입력 창
            PasswordTextField(
                password = password,
                onPasswordChange = { password = it },
                showPassword = showPassword,
                onTogglePasswordVisibility = { showPassword = !showPassword },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )

            // 로그인하기 버튼
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .background(loginButtonColor, RoundedCornerShape(5.dp))
                    .padding(vertical = 14.dp)
                    .noRippleClickable {
                        if (isValidProfile) {
                            // 회원 정보가 유효 하지 않을 시 스낵바
                            scope.launch {
                                snackbarHostState.showSnackbar("회원 정보가 유효하지 않습니다.")
                            }
                        } else { // 회원가입 정보 일치 시
                            // 자동 로그인 정보 저장
                            autoLogin.saveLoginInfo(id, password)
                            // MainActivity로 이동
                            val intent = Intent(context, MainActivity::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            context.startActivity(intent)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.button_sign_in),
                    fontWeight = FontWeight.Bold,
                    color = loginTextColor
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 35.dp)
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.button_find_id),
                    color = Color.Gray
                )
                VerticalDivider(
                    thickness = 1.dp,
                    color = Color.Gray
                )
                Text(
                    text = stringResource(R.string.button_find_pw),
                    color = Color.Gray
                )
                VerticalDivider(
                    thickness = 1.dp,
                    color = Color.Gray
                )
                Text(
                    text = stringResource(R.string.button_sign_up),
                    color = Color.Gray,
                    modifier = Modifier.clickable {
                        navigateToSignUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSignInScreen() {
    SignInScreen()
}
