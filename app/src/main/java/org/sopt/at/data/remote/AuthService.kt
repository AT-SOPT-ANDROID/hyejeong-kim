package org.sopt.at.data.remote

import org.sopt.at.data.model.request.SignInRequest
import org.sopt.at.data.model.request.SignUpRequest
import org.sopt.at.data.model.response.SignInResponse
import org.sopt.at.data.model.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    // 회원가입 API
    @POST("/api/v1/auth/signup")
    suspend fun postSignUp(
        @Body request: SignUpRequest
    ): Response<SignUpResponse>

    // 로그인 API
    @POST("/api/v1/auth/signin")
    suspend fun postLogin(
        @Body request: SignInRequest
    ): Response<SignInResponse>
}