package org.sopt.at

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.signup.SignUpActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val signUpId = intent.getStringExtra("ID") ?: ""
        val signUpPw = intent.getStringExtra("PW") ?: ""

        setContent {
            ATSOPTANDROIDTheme {
                LoginUi(signUpId, signUpPw)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginUi(signUpId: String = "", signUpPw: String = "", modifier: Modifier = Modifier) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }
    val context = LocalContext.current

    // 스낵바
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // 포커스
    val focusRequester = remember { FocusRequester() }

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
                .padding(horizontal = 15.dp, vertical = 100.dp)
        ) {
            Text(
                text = "TVING ID 로그인",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // id 입력 창
            TextField(
                value = id,
                onValueChange = { id = it },
                placeholder = { Text("아이디") },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                shape = RoundedCornerShape(5.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF262626),
                    unfocusedContainerColor = Color(0xFF262626),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.White,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray
                ),
                textStyle = TextStyle(
                    color = Color.White
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    // 다음 버튼 클릭 시 비밀번호 textfield로 포커스 이동
                    onNext = {
                        focusRequester.requestFocus()
                    }
                )
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
                    .focusRequester(focusRequester) // 포커스 지정
            )

            // 로그인하기 버튼
            Button(
                onClick = {
                    if (id != signUpId || password != signUpPw || signUpId.isBlank() || signUpPw.isBlank()) {
                        // 회원 정보가 유효 하지 않을 시 스낵바
                        scope.launch {
                            snackbarHostState.showSnackbar("회원 정보가 유효하지 않습니다.")
                        }
                    } else {
                        // 회원가입 정보 일치 시 MyActivity로 이동
                        val intent = Intent(context, MyActivity::class.java).apply {
                            putExtra("Profile", id)
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .height(45.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF404040),
                    contentColor = Color.Gray
                ),
            ) {
                Text(
                    "로그인하기",
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(top = 35.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "아이디 찾기",
                    color = Color.Gray
                )
                Text(
                    text = "|",
                    color = Color.Gray
                )
                Text(
                    text = "비밀번호 찾기",
                    color = Color.Gray
                )
                Text(
                    text = "|",
                    color = Color.Gray
                )
                Text(
                    text = "회원가입",
                    color = Color.Gray,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, SignUpActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        context.startActivity(intent)
                    }
                )
            }
        }
    }

}

// 비밀번호 TextField 함수
@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    showPassword: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        placeholder = { Text("비밀번호") },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = onTogglePasswordVisibility) {
                Icon(
                    imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (showPassword) "Hide password" else "Show password",
                    tint = Color.Gray
                )
            }
        },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF262626),
            unfocusedContainerColor = Color(0xFF262626),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray
        ),
        textStyle = TextStyle(
            color = Color.White
        ),
        singleLine = true
    )
}