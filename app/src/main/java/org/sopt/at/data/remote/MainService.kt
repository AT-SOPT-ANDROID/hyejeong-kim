package org.sopt.at.data.remote

import org.sopt.at.data.model.response.MyNicknameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MainService {

    // 내 닉네임 조회
    @GET("/api/v1/users/me")
    suspend fun getMyNickname(
        @Header("userId") token: Long
    ): Response<MyNicknameResponse>
}