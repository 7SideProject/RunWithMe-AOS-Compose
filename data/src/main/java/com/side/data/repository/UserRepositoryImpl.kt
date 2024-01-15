package com.side.data.repository

import com.side.data.datasource.user.UserDataSource
import com.side.data.mapper.mapperToEmailLoginRequest
import com.side.data.mapper.mapperToUser
import com.side.data.utils.asResultOtherType
import com.side.domain.model.User
import com.side.domain.repository.UserRepository
import com.side.domain.repository.UserTypeResponse
import com.side.domain.utils.ResultType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun loginWithEmail(user: User): Flow<UserTypeResponse> =
        userDataSource.loginWithEmail(user.mapperToEmailLoginRequest()).asResultOtherType {
            ResultType.Success(
                it.changeData(it.data?.mapperToUser())
            )
        }


}