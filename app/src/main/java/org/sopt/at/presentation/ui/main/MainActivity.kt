package org.sopt.at.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.at.core.navigation.LocalNavController
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

// Application 클래스에 Hilt를 설정하고 애플리케이션 수준 구성요소를 사용할 수 있게 되면 Hilt는 이 주석이 있는 다른 Android 클래스에 종송 항목 제공 가능
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            CompositionLocalProvider(LocalNavController provides navController) {
                ATSOPTANDROIDTheme {
                    MainScreen()
                }
            }
        }
    }
}
