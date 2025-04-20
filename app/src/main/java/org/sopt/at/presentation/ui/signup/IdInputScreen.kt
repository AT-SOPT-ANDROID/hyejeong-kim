package org.sopt.at.presentation.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.at.core.component.textfield.IdTextField

@Composable
fun IdInputScreen(
    modifier: Modifier = Modifier,
    id: String,
    onIdChange: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit,
) {
    // id 유효성 검사: 영문 소문자 또는 영문 소문자, 숫자 조합 6~12자리
    val isValidId = id.matches(Regex("^(?=.*[a-z])[a-z0-9]{6,12}$"))

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
                .padding(15.dp),
        ) {
            // 뒤로 가기 버튼
            // 클릭 시 로그인 뷰로 이동
            Box(
                modifier = Modifier.size(24.dp)
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "뒤로가기",
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp)
                    .imePadding(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // id 입력
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
                        IdTextField(
                            id = id,
                            onIdChange = onIdChange,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.Gray,
                                    shape = RoundedCornerShape(5.dp)
                                )
                        )

                        Text(
                            text = "영문 소문자 또는 영문 소문자, 숫자 조합 6~12 자리",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                    }
                }

                // 다음 버튼
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
                            // id가 유효할 경우 회원가입 비밀번호 뷰로 이동
                            if (isValidId) {
                                onNext()
                            } else {
                                // id가 유효 하지 않을 시 스낵바
                                scope.launch {
                                    snackbarHostState.showSnackbar("ID가 유효하지 않습니다.")
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "다음",
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}