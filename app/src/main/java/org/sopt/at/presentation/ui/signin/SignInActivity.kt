package org.sopt.at.presentation.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.presentation.ui.my.MyActivity
import org.sopt.at.presentation.ui.signup.SignUpActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

const val ID_KEY = "ID"
const val PW_KEY = "PW"

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
                    userId = result.data?.getStringExtra(ID_KEY) ?: ""
                    userPw = result.data?.getStringExtra(PW_KEY) ?: ""
                }
            }

        val autoLogin = AutoLogin(this)

        // 로그인 되어 있을 경우 My 뷰로 이동
        if (autoLogin.isLoggedIn()) {
            val intent = Intent(this, MyActivity::class.java).apply {
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
