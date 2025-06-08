package org.sopt.at.data.remote

import org.sopt.at.data.model.request.EditNicknameRequest
import org.sopt.at.data.model.response.EditNicknameResponse
import org.sopt.at.data.model.response.MyNicknameResponse
import org.sopt.at.data.model.response.UsersResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Query

interface MainService {

    // 내 닉네임 조회
    @GET("/api/v1/users/me")
    suspend fun getMyNickname(
        @Header("userId") token: Long
    ): Response<MyNicknameResponse>

    // 닉네임 조회
    @GET("/api/v1/users")
    suspend fun getUsers(
        @Query("keyword") query: String
    ): Response<UsersResponse>

    // 닉네임 수정
    @PATCH("/api/v1/users")
    suspend fun patchNickname(
        @Header("userId") token: Long,
        @Body request: EditNicknameRequest
    ): Response<EditNicknameResponse>
}