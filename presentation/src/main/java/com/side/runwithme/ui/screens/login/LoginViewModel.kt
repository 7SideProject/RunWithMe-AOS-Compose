package com.side.runwithme.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.side.domain.model.User
import com.side.domain.usecase.LoginWithEmailUseCase
import com.side.runwithme.utils.handleHttpException
import com.side.runwithme.viewmodel.EventViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithEmailUseCase: LoginWithEmailUseCase
) : EventViewModel() {

    fun loginWithEmail(email: String, password: String) {
        viewModelScope.launch {
            loginWithEmailUseCase(User(email, password)).collectLatest {
                it.onSuccess {
                    this.launch {
                        _eventFlow.emit(Event.Success(it.code))
                    }
                }.onError { exception ->
                    if(exception is HttpException) {
                        handleHttpException<User?>(exception) { response ->
                            this.launch {
                                _eventFlow.emit(Event.Success(response.code))
                            }
                        }
                    }else {
                        this.launch {
                            _eventFlow.emit(Event.Error)
                        }
                    }
                }
            }
        }
    }
}