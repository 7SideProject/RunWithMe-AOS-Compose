package com.side.data.mapper

import com.side.data.model.request.EmailLoginRequest
import com.side.data.model.response.LoginResponse
import com.side.domain.model.User

fun LoginResponse.mapperToUser(): User =
    User(seq, email, nickname, height, weight, point)

fun User.mapperToEmailLoginRequest(): EmailLoginRequest = EmailLoginRequest(email, password)