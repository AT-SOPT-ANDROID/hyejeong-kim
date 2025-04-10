package org.sopt.at

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class MyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val profileId = intent.getStringExtra("Profile") ?: ""

        setContent {
            ATSOPTANDROIDTheme {
                MyUi(profileId)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyUi(profileId: String = "", modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = profileId,
            color = Color.White,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 30.dp)
        )

        // 로그아웃 버튼
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(50.dp)
                .background(Color.Black, RoundedCornerShape(10.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .clickable(
                    // 리플 효과 제거
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    // 로그아웃 버튼 클릭 시 로그인 뷰로 이동
                    val intent = Intent(context, SignInActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "로그아웃",
                fontSize = 16.sp,
                color = Color.LightGray
            )
        }
    }

}