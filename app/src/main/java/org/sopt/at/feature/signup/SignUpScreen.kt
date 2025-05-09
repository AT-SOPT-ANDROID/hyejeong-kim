package org.sopt.at.feature.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.SignUpRequest
import org.sopt.at.feature.signup.content.SignUpContent

@Composable
fun SignUpRoute(
    navigateToSignIn: () -> Unit,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    SignUpScreen(
        navigateToSignIn = navigateToSignIn,
        showSnackbar = showSnackbar,
        modifier = modifier
    )
}

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentStep by remember { mutableStateOf(1) }
    var nickname by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val viewModel: SignUpViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        when (uiState) {
            is BaseState.Error -> {
                showSnackbar((uiState as BaseState.Error).message)
            }

            is BaseState.Success -> {
                navigateToSignIn()
            }

            else -> Unit
        }
    }

    when (currentStep) {
        1 -> SignUpContent(
            step = currentStep,
            nickname = nickname,
            onNicknameChange = { nickname = it },
            onNext = {
                currentStep = 2
            },
            onBack = {
                navigateToSignIn()
            },
            showErrorSnackbar = showSnackbar
        )

        2 -> SignUpContent(
            step = currentStep,
            id = id,
            onIdChange = { id = it },
            onNext = { currentStep = 3 },
            onBack = { currentStep = 1 },
            showErrorSnackbar = showSnackbar
        )

        else -> SignUpContent(
            step = currentStep,
            pw = pw,
            onPwChange = { pw = it },
            onNext = {
                val request = SignUpRequest(nickname = nickname, loginId = id, password = pw)
                viewModel.sendEvent(SignUpEvent.PostSignUp(request))
            },
            onBack = { currentStep = 2 },
            showErrorSnackbar = showSnackbar
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewSignUpScreen() {
    SignUpScreen(
        navigateToSignIn = {},
        showSnackbar = {}
    )
}