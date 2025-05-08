package org.sopt.at.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class EditNicknameResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: String? = null
)
