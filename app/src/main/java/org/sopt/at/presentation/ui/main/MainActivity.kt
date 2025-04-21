package org.sopt.at.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import androidx.core.view.WindowCompat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            ATSOPTANDROIDTheme {
                MainScreen()
            }
        }
    }
}
