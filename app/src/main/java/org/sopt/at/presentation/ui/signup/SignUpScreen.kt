package org.sopt.at.presentation.ui.signup

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
import org.sopt.at.core.navigation.LocalNavController
import org.sopt.at.core.util.IntentKeys
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.SignUpRequest
import org.sopt.at.presentation.ui.signup.content.SignUpContent

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit = {},
    showSnackbar: (String) -> Unit = {}
) {
    val navController = LocalNavController.current

    var currentStep by remember { mutableStateOf(1) }
    var nickname by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val viewModel: SignUpViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        when (uiState) {
            is BaseState.Error -> {}
            is BaseState.Success<*> -> {
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
                navController.popBackStack()
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
                navController.previousBackStackEntry?.savedStateHandle?.set(IntentKeys.ID_KEY, id)
                navController.previousBackStackEntry?.savedStateHandle?.set(IntentKeys.PW_KEY, pw)
                val request = SignUpRequest(nickname = nickname, loginId = id, password = pw)
                viewModel.processIntent(SignUpIntent.PostSignUp(request))
                navigateToSignIn()
            },
            onBack = { currentStep = 2 },
            showErrorSnackbar = showSnackbar
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewSignUpScreen() {
    SignUpScreen()
}