package org.sopt.at.presentation.ui.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.core.navigation.LocalNavController
import org.sopt.at.core.util.IntentKeys
import org.sopt.at.presentation.ui.signup.content.SignUpContent

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit = {},
    showSnackbar: (String) -> Unit = {}
) {
    val navController = LocalNavController.current

    var currentStep by remember { mutableStateOf(1) }
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    when (currentStep) {
        1 -> SignUpContent(
            step = currentStep,
            id = id,
            onIdChange = { id = it },
            onNext = { currentStep = 2 },
            onBack = {
                navController.popBackStack()
            },
            showErrorSnackbar = showSnackbar
        )

        else -> SignUpContent(
            step = currentStep,
            pw = pw,
            onPwChange = { pw = it },
            onNext = {
                navController.previousBackStackEntry?.savedStateHandle?.set(IntentKeys.ID_KEY, id)
                navController.previousBackStackEntry?.savedStateHandle?.set(IntentKeys.PW_KEY, pw)
                navigateToSignIn()
            },
            onBack = { currentStep = 1 },
            showErrorSnackbar = showSnackbar
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewSignUpScreen() {
    SignUpScreen()
}