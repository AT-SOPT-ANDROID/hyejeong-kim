package org.sopt.at.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.SignUpRequest
import org.sopt.at.data.model.response.SignUpResponse
import org.sopt.at.data.model.runRemote
import org.sopt.at.data.remote.UserService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) :
    UserRepository {

    override suspend fun postSignUp(request: SignUpRequest): Flow<BaseState<SignUpResponse>> =
        runRemote { userService.postSignUp(request) }

}