package org.sopt.at.data.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditNicknameRequest(
    @SerialName("nickname")
    val nickname: String
)
