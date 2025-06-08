package org.sopt.at.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignUpData
)

@Serializable
data class SignUpData(
    @SerialName("userId")
    val userId: Long,
    @SerialName("nickname")
    val nickname: String
)