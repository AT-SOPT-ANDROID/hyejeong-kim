package org.sopt.at.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: LoginData
)

@Serializable
data class LoginData(
    @SerialName("userId")
    val userId: Long
)