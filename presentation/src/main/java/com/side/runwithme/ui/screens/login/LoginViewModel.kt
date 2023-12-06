package com.side.runwithme.ui.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.side.domain.model.User
import com.side.domain.usecase.LoginWithEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithEmailUseCase: LoginWithEmailUseCase
): ViewModel() {

    fun loginWithEmail(email: String, password: String){
        viewModelScope.launch(Dispatchers.IO) {
            loginWithEmailUseCase(User(email, password)).collectLatest {
                it.onSuccess {

                }.onFailure {

                }.onError {

                }
            }
        }
    }

}