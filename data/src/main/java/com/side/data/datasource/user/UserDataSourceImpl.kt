package com.side.data.datasource.user

import com.side.data.api.LoginApi
import com.side.data.model.request.EmailLoginRequest
import com.side.data.model.response.LoginResponse
import com.side.domain.base.BaseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataSourceImpl @Inject constructor(
    private val loginApi: LoginApi
) : UserDataSource {

    override fun loginWithEmail(emailLoginRequest: EmailLoginRequest): Flow<BaseResponse<LoginResponse?>> =
        flow {
            emit(loginApi.loginWithEmail(emailLoginRequest))
        }
}