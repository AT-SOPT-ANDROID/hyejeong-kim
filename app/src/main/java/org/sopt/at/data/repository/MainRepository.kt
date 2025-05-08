package org.sopt.at.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.EditNicknameRequest
import org.sopt.at.data.model.response.EditNicknameResponse
import org.sopt.at.data.model.response.MyNicknameResponse
import org.sopt.at.data.model.response.UsersResponse

interface MainRepository {
    suspend fun getMyNickname(token: Long): Flow<BaseState<MyNicknameResponse>>

    suspend fun getUsers(query: String): Flow<BaseState<UsersResponse>>

    suspend fun patchNickname(
        token: Long,
        request: EditNicknameRequest
    ): Flow<BaseState<EditNicknameResponse>>
}