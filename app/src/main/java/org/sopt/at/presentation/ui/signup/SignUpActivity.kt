package org.sopt.at.presentation.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.at.presentation.ui.signin.ID_KEY
import org.sopt.at.presentation.ui.signin.PW_KEY
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
                            putExtra(ID_KEY, id)
                            putExtra(PW_KEY, pw)
                        }
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                )
            }
        }
    }
}
