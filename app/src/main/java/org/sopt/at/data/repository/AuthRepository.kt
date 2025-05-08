package org.sopt.at.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.model.BaseState
import org.sopt.at.data.model.request.SignInRequest
import org.sopt.at.data.model.request.SignUpRequest
import org.sopt.at.data.model.response.SignInResponse
import org.sopt.at.data.model.response.SignUpResponse

interface AuthRepository {
    suspend fun postSignUp(request: SignUpRequest): Flow<BaseState<SignUpResponse>>

    suspend fun postSignIn(request: SignInRequest): Flow<BaseState<SignInResponse>>
}