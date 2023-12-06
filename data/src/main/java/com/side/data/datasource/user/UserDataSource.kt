package com.side.data.datasource.user

import com.side.data.model.request.EmailLoginRequest
import com.side.data.model.response.LoginResponse
import com.side.domain.base.BaseResponse
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

    fun loginWithEmail(emailLoginRequest: EmailLoginRequest): Flow<BaseResponse<LoginResponse?>>

}