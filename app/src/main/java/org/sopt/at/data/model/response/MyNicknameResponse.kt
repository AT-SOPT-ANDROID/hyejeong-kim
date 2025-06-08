package org.sopt.at.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyNicknameResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: MyNicknameData
)

@Serializable
data class MyNicknameData(
    @SerialName("nickname")
    val nickname: String
)