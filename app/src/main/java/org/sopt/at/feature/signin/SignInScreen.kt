package org.sopt.at.feature.signin

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
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.sopt.at.R
import org.sopt.at.core.component.textfield.BasicTextField
import org.sopt.at.core.component.textfield.PasswordTextField
import org.sopt.at.core.util.noRippleClickable
import org.sopt.at.data.model.request.SignInRequest
import org.sopt.at.ui.theme.TvingTheme

@Composable
fun SignInRoute(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignInScreen(
        modifier = modifier,
        navigateToSignUp = navigateToSignUp,
        navigateToHome = navigateToHome,
        showSnackbar = showSnackbar
    )
}

@Composable
fun SignInScreen(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: SignInViewModel = hiltViewModel()

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }

    // 로그인 버튼 색깔
    val isFormFilled = id.isNotBlank() && password.isNotBlank()
    val loginButtonColor = if (isFormFilled) TvingTheme.colors.BrandRed else TvingTheme.colors.Gray4
    val loginTextColor = if (isFormFilled) TvingTheme.colors.BasicWhite else TvingTheme.colors.Gray2

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                SignInEffect.NavigateToHome -> navigateToHome()
                is SignInEffect.ShowSnackbar -> showSnackbar(it.message)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(top = 100.dp)
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.sign_in_title),
            color = TvingTheme.colors.BasicWhite,
            style = TvingTheme.typography.title
        )

        // 아이디 입력 창
        BasicTextField(
            value = id,
            onValueChange = { id = it },
            placeholderText = stringResource(R.string.textfield_id),
            modifier = Modifier
                .padding(top = 20.dp)
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
        )

        // 로그인하기 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(loginButtonColor, RoundedCornerShape(5.dp))
                .padding(vertical = 14.dp)
                .noRippleClickable {
                    viewModel.sendEvent(SignInEvent.PostLogin(SignInRequest(id, password)))
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.button_sign_in),
                style = TvingTheme.typography.button,
                color = loginTextColor
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 35.dp)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.button_find_id),
                color = TvingTheme.colors.Gray2,
                style = TvingTheme.typography.caption
            )
            VerticalDivider(
                thickness = 1.dp,
                color = TvingTheme.colors.Gray2,
            )
            Text(
                text = stringResource(R.string.button_find_pw),
                color = TvingTheme.colors.Gray2,
                style = TvingTheme.typography.caption
            )
            VerticalDivider(
                thickness = 1.dp,
                color = TvingTheme.colors.Gray2
            )
            Text(
                text = stringResource(R.string.button_sign_up),
                color = TvingTheme.colors.Gray2,
                style = TvingTheme.typography.caption,
                modifier = Modifier.clickable {
                    navigateToSignUp()
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewSignInScreen() {
    SignInScreen(
        navigateToSignUp = {},
        navigateToHome = {},
        showSnackbar = {}
    )
}
