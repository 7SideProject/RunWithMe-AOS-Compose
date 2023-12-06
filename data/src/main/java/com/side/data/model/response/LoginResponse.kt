package com.side.data.model.response

data class LoginResponse(
    val seq: Long,
    val email: String,
    val nickname: String,
    val height: Int,
    val weight: Int,
    val point: Int
)