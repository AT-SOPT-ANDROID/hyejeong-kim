package org.sopt.at

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IdInputScreen(
    id: String,
    onIdChange: (String) -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(horizontal = 15.dp, vertical = 100.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "아이디를 입력해주세요.",
                color = Color.White,
                fontSize = 20.sp
            )

            Column {
                TextField(
                    value = id,
                    onValueChange = onIdChange,
                    placeholder = { Text("아이디") },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                Text(
                    text = "영문 소문자 또는 영문 소문자, 숫자 조합 6~12 자리",
                    color = Color.White
                )
            }
        }

        Button(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(1.dp, Color.Gray),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.LightGray
            )
        ) {
            Text("다음")
        }
    }

}