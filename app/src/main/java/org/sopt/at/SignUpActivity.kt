package org.sopt.at

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ATSOPTANDROIDTheme {
                SignUpScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    when (currentStep) {
        1 -> IdInputScreen(
            id, { id = it },
            onNext = { currentStep = 2 },
        )

        else -> PwInputScreen(pw, { pw = it })
    }

}