package org.sopt.at.data.model

data class ErrorResponse(
    val success: Boolean,
    val code: String,
    val message: String
)
