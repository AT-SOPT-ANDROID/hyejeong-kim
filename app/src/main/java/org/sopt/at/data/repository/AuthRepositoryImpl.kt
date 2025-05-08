package org.sopt.at.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.SignInRequest
import org.sopt.at.data.model.request.SignUpRequest
import org.sopt.at.data.model.response.SignInResponse
import org.sopt.at.data.model.response.SignUpResponse
import org.sopt.at.data.model.runRemote
import org.sopt.at.data.remote.AuthService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authService: AuthService) :
    AuthRepository {

    override suspend fun postSignUp(request: SignUpRequest): Flow<BaseState<SignUpResponse>> =
        runRemote { authService.postSignUp(request) }

    override suspend fun postSignIn(request: SignInRequest): Flow<BaseState<SignInResponse>> =
        runRemote { authService.postLogin(request) }

}