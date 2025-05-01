package org.sopt.at.presentation.ui.main.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit
) {
    val context = LocalContext.current
    val autoLogin = AutoLogin(context)

    var logoutRequest by remember { mutableStateOf(false) }

    if (logoutRequest) {
        LaunchedEffect(Unit) {
            // 자동 로그인 정보 제거
            autoLogin.logout()
            // 로그인 뷰로 이동
            navigateToSignIn()
        }
    }

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
                logoutRequest = true
            },
            content = stringResource(R.string.my_logout_button)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewMyScreen() {
    MyScreen(
        navigateToSignIn = {}
    )
}