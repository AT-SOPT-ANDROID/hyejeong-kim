package org.sopt.at.presentation.ui.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.presentation.ui.signup.content.SignUpContent

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit = {},
    navigateToSignIn: (String, String) -> Unit = { _, _ -> }
) {
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
                navigateToBack()
            }
        )

        else -> SignUpContent(
            step = currentStep,
            pw = pw,
            onPwChange = { pw = it },
            onNext = {
                navigateToSignIn(id, pw)
            }, onBack = { currentStep = 1 })
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewSignUpScreen() {
    SignUpScreen(
        navigateToBack = {},
        navigateToSignIn = { _, _ -> }
    )
}