package com.side.data.api

import com.side.data.model.request.EmailLoginRequest
import com.side.data.model.response.LoginResponse
import com.side.domain.base.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("users/login")
    suspend fun loginWithEmail(@Body request: EmailLoginRequest): BaseResponse<LoginResponse?>
}