package org.sopt.at.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: UsersData
)

@Serializable
data class UsersData(
    @SerialName("nicknameList")
    val nicknameList: List<String>
)