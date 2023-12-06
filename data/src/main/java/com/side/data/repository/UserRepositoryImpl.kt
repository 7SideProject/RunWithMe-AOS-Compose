package com.side.data.repository

import com.side.data.datasource.user.UserDataSource
import com.side.data.mapper.mapperToEmailLoginRequest
import com.side.data.mapper.mapperToUser
import com.side.data.utils.asResult
import com.side.data.utils.asResultOtherType
import com.side.domain.model.User
import com.side.domain.repository.UserRepository
import com.side.domain.repository.UserTypeResponse
import com.side.domain.utils.ResultType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override fun loginWithEmail(user: User): Flow<UserTypeResponse> =
        userDataSource.loginWithEmail(user.mapperToEmailLoginRequest()).asResultOtherType {
            ResultType.Success(it.changeData(it.data))
            when(it.code){
                100 -> {
                    ResultType.Success(
                        it.changeData(it.data?.mapperToUser())
                    )
                }
                else -> {
                    ResultType.Fail(it.changeData(null))
                }
            }
        }




}