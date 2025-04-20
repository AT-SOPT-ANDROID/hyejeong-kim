package org.sopt.at.presentation.ui.signup

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.presentation.ui.signin.SignInActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                SignUpScreen(
                    navigateToBack = {
                        // 뒤로가기 버튼 클릭 시 로그인 뷰로 이동
                        val intent = Intent(this, SignInActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        startActivity(intent)
                    },
                    navigateToSignIn = { id, pw ->
                        // 다음 버튼 클릭 시 로그인 뷰로 이동
                        val intent = Intent().apply {
                            putExtra("ID", id)
                            putExtra("PW", pw)
                        }
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier,
                 navigateToBack: () -> Unit = {},
                 navigateToSignIn: (String, String) -> Unit = {_, _ ->}) {
    var currentStep by remember { mutableStateOf(1) }
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    when (currentStep) {
        1 -> IdInputScreen(
            id, { id = it },
            onNext = { currentStep = 2 },
            onBack = {
                navigateToBack()
            }
        )

        else -> PwInputScreen(pw, { pw = it }, onNext = {
            navigateToSignIn(id, pw)
        }, onBack = { currentStep = 1 })
    }

}