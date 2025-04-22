package org.sopt.at.presentation.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.at.core.util.AutoLogin
import org.sopt.at.core.util.IntentKeys
import org.sopt.at.presentation.ui.main.MainActivity
import org.sopt.at.presentation.ui.signup.SignUpActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignInActivity : ComponentActivity() {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private var userId: String = ""
    private var userPw: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    userId = result.data?.getStringExtra(IntentKeys.ID_KEY) ?: ""
                    userPw = result.data?.getStringExtra(IntentKeys.PW_KEY) ?: ""
                }
            }

        val autoLogin = AutoLogin(this)

        // 로그인 되어 있을 경우 Main 뷰로 이동
        if (autoLogin.isLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("Profile", userId)
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            return
        }

        setContent {
            ATSOPTANDROIDTheme {
                SignInScreen(
                    signUpId = userId,
                    signUpPw = userPw,
                    navigateToSignUp = {
                        val intent = Intent(this, SignUpActivity::class.java)
                        resultLauncher.launch(intent)
                    })
            }
        }
    }
}
