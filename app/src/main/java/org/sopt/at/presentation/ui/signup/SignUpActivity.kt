package org.sopt.at.presentation.ui.signup

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
                SignUpScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    val context = LocalContext.current

    when (currentStep) {
        1 -> IdInputScreen(
            id, { id = it },
            onNext = { currentStep = 2 },
            onBack = {
                // 뒤로가기 버튼 클릭 시 로그인 뷰로 이동
                val intent = Intent(context, SignInActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            }
        )

        else -> PwInputScreen(pw, { pw = it }, onNext = {
            // 다음 버튼 클릭 시 로그인 뷰로 이동
            val intent = Intent(context, SignInActivity::class.java).apply {
                putExtra("ID", id)
                putExtra("PW", pw)
                flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }, onBack = { currentStep = 1 })
    }

}