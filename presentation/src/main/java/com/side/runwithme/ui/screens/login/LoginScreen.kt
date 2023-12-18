package com.side.runwithme.ui.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.side.runwithme.R
import com.side.runwithme.components.EmailInput
import com.side.runwithme.components.PasswordInput
import com.side.runwithme.components.RoundedStrongButton
import com.side.runwithme.components.RoundedWhiteButton
import com.side.runwithme.components.VerticalSpacer
import com.side.runwithme.navigation.BottomNavItem
import com.side.runwithme.ui.theme.RunWithMeTheme
import com.side.runwithme.utils.ResponseCodeStatus
import com.side.runwithme.viewmodel.EventViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    RunWithMeTheme {
        LoginScreen(navController, loginViewModel = hiltViewModel())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val loginSuccessMsg = stringResource(id = R.string.login_success)
    val loginFailMsg = stringResource(id = R.string.login_fail)
    val loginErrorMsg = stringResource(id = R.string.login_error)

    val email = rememberSaveable {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        loginViewModel.eventFlow.collectLatest {
            when (it) {
                is EventViewModel.Event.Success -> {
                    if (it.code == ResponseCodeStatus.LOGIN_SUCCESS.code) {
                        Toast.makeText(context, loginSuccessMsg, Toast.LENGTH_SHORT).show()
                        navController.navigate(BottomNavItem.Home.screenRoute)
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar(loginFailMsg)
                        }
                    }
                }

                is EventViewModel.Event.Error -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(loginErrorMsg)
                    }
                }

                is EventViewModel.Event.Loading -> {

                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.img_login),
                    contentScale = ContentScale.FillHeight
                )
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Icon(
                    modifier = Modifier.size(160.dp),
                    painter = painterResource(id = R.drawable.login_logo),
                    contentDescription = "RunWithMeLogo"
                )

                VerticalSpacer(104)

                EmailInput(emailState = email)

                VerticalSpacer(16)

                PasswordInput(
                    passwordState = password,
                    labelId = stringResource(id = R.string.password),
                    enabled = true,
                    passwordVisibility = passwordVisibility
                )

                VerticalSpacer(16)

                LoginButton {
                    loginViewModel.loginWithEmail(email = email.value, password = password.value)
                }

                VerticalSpacer(16)

                JoinButton {
                    navController.navigate(BottomNavItem.Home.screenRoute)
                }
            }
        }
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    RoundedStrongButton(
        stringResource(id = R.string.login)
    ) {
        onClick.invoke()
    }
}

@Composable
fun JoinButton(onClick: () -> Unit) {
    RoundedWhiteButton(
        stringResource(id = R.string.join)
    ) {
        onClick.invoke()
    }
}

fun showSnackBar(scope: CoroutineScope, snackbarHostState: SnackbarHostState, msg: String) {
    scope.launch {
        snackbarHostState.showSnackbar(msg)
    }
}