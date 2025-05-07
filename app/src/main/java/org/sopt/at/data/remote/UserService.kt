package org.sopt.at.data.remote

import org.sopt.at.data.model.request.SignUpRequest
import org.sopt.at.data.model.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/api/v1/auth/signup")
    suspend fun postSignUp(
        @Body request: SignUpRequest
    ): Response<SignUpResponse>
}