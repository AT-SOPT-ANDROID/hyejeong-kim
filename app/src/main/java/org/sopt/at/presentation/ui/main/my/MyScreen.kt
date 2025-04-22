package org.sopt.at.presentation.ui.main.my

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import org.sopt.at.core.component.button.TvingButton
import org.sopt.at.core.util.AutoLogin
import org.sopt.at.core.util.IntentKeys
import org.sopt.at.presentation.ui.signin.SignInActivity

@Composable
fun MyScreen(
    modifier: Modifier = Modifier
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
            text = autoLogin.getLoginInfo(IntentKeys.ID_KEY),
            color = Color.White,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 30.dp)
        )

        // 로그아웃 버튼
        TvingButton(
            onClick = {
                // 자동 로그인 정보 제거
                autoLogin.logout()
                // 로그인 뷰로 이동
                val intent = Intent(context, SignInActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)
            },
            content = stringResource(R.string.my_logout_button)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMyScreen() {
    MyScreen()
}