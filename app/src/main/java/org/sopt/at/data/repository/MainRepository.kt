package org.sopt.at.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.response.MyNicknameResponse

interface MainRepository {
    suspend fun getMyNickname(token: Long): Flow<BaseState<MyNicknameResponse>>

}