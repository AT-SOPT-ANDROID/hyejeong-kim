package org.sopt.at.presentation.ui.main.my

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.at.R
import org.sopt.at.core.component.button.TvingButton

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit
) {
    val viewModel: MyViewModel = hiltViewModel()
    val nickname by viewModel.nickname.collectAsState()
    val effect = viewModel.effect.collectAsStateWithLifecycle(null)

    LaunchedEffect(effect.value) {
        when (effect.value) {
            MyEffect.NavigateToSignIn -> navigateToSignIn()
            else -> {}
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
            text = nickname.toString(),
            color = Color.White,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 30.dp)
        )

        // 로그아웃 버튼
        TvingButton(
            onClick = {
                viewModel.sendEvent(MyEvent.OnLogoutClick)
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