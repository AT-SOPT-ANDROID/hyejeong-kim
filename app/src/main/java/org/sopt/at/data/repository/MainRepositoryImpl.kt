package org.sopt.at.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.EditNicknameRequest
import org.sopt.at.data.model.response.EditNicknameResponse
import org.sopt.at.data.model.response.MyNicknameResponse
import org.sopt.at.data.model.response.UsersResponse
import org.sopt.at.data.model.runRemote
import org.sopt.at.data.remote.MainService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val mainService: MainService) :
    MainRepository {

    override suspend fun getMyNickname(token: Long): Flow<BaseState<MyNicknameResponse>> =
        runRemote { mainService.getMyNickname(token) }

    override suspend fun getUsers(query: String): Flow<BaseState<UsersResponse>> =
        runRemote { mainService.getUsers(query) }

    override suspend fun patchNickname(
        token: Long,
        request: EditNicknameRequest
    ): Flow<BaseState<EditNicknameResponse>> =
        runRemote { mainService.patchNickname(token, request) }
}