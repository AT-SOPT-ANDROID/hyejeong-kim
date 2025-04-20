package org.sopt.at.presentation.ui.my

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.presentation.ui.signin.SignInActivity
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.util.AutoLogin

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val profileId = intent.getStringExtra("Profile").orEmpty()

        setContent {
            ATSOPTANDROIDTheme {
                MyUi(profileId = profileId)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyUi(
    modifier: Modifier = Modifier,
    profileId: String = ""
) {
    val context = LocalContext.current
    val autoLogin = AutoLogin(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = autoLogin.getLoginInfo("userId"),
            color = Color.White,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 30.dp)
        )

        // 로그아웃 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black, shape = RoundedCornerShape(10.dp))
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                .padding(vertical = 14.dp)
                .noRippleClickable { // 로그아웃 버튼 클릭 시
                    // 자동 로그인 정보 제거
                    autoLogin.logout()
                    // 로그인 뷰로 이동
                    val intent = Intent(context, SignInActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.my_logout_button),
                fontSize = 16.sp,
                color = Color.LightGray
            )
        }
    }

}