package org.sopt.at.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignUpUser
)

@Serializable
data class SignUpUser(
    @SerialName("userId")
    val userId: Int,
    @SerialName("nickname")
    val nickname: String
)