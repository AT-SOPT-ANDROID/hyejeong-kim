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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun IdInputScreen(
    id: String,
    onIdChange: (String) -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    // id 유효성 검사: 영문 소문자 또는 영문 소문자, 숫자 조합 6~12자리
    val isValidId = id.matches(Regex("^[a-z0-9]{6,12}$"))

    // 스낵바
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(innerPadding)
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
                    // 아이디 입력 창
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
                onClick =  {
                    if (isValidId) {
                        onNext()
                    } else {
                        // id가 유효 하지 않을 시 스낵바
                        scope.launch {
                            snackbarHostState.showSnackbar("ID가 유효하지 않습니다.")
                        }
                    }
                },
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

}