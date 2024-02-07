package com.side.runwithme.ui.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.side.runwithme.R
import com.side.runwithme.components.EmailInput
import com.side.runwithme.components.PasswordInput
import com.side.runwithme.components.RoundedKakaoButton
import com.side.runwithme.components.RoundedStrongButton
import com.side.runwithme.components.VerticalSpacer
import com.side.runwithme.navigation.BottomNavItem
import com.side.runwithme.navigation.RunWithMeScreens
import com.side.runwithme.ui.theme.RunWithMeTheme
import com.side.runwithme.utils.ResponseCodeStatus
import com.side.runwithme.viewmodel.EventViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//240128 프리뷰 변경
//@Preview(showBackground = true)
//@Composable
//fun LoginPreview() {
//    val navController = rememberNavController()
//    RunWithMeTheme {
//        LoginScreen(navController, loginViewModel = hiltViewModel())
//    }
//}

@Preview
@Composable
fun PreviewLoginScreenDesign() {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    RunWithMeTheme {
        LoginScreenDesign(snackbarHostState, navController) { _, _ -> }
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

    LoginScreenDesign(
        navController = navController,
        snackbarHostState = snackbarHostState,
        loginWithEmail = { email, password -> loginViewModel.loginWithEmail(email, password) }
    )
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
fun KakaoLoginButton(onClick: () -> Unit) {
    RoundedKakaoButton(
        stringResource(id = R.string.kakao_login)
    ) {
        onClick.invoke()
    }
}

fun showSnackBar(scope: CoroutineScope, snackbarHostState: SnackbarHostState, msg: String) {
    scope.launch {
        snackbarHostState.showSnackbar(msg)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreenDesign(
    snackbarHostState: SnackbarHostState,
    navController: NavController,
    loginWithEmail: (String, String) -> Unit
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Image(
            painter = painterResource(R.drawable.img_login),
            contentDescription = "RunWithMeLoginBackgroundImage",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier
                    .height(160.dp)
                    .weight(0.1f),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(160.dp)
                        .weight(1f),
                    painter = painterResource(id = R.drawable.login_logo2),
                    contentDescription = "RunWithMeLogo"
                )
                Spacer(
                    modifier = Modifier
                        .size(1.dp)
                        .weight(0.5f)
                )
            }

            Column(
                Modifier.widthIn(max = 500.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
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
                    loginWithEmail(email.value, password.value)
                }

                VerticalSpacer(8)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .weight(0.1f)
                    )
                    Button(
                        onClick = { navController.navigate(RunWithMeScreens.JoinScreen1.name) },
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.join),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(16.dp)
                                .shadow(elevation = 5.dp)
                        )
                    }
                }

                VerticalSpacer(32)

                KakaoLoginButton {
                    navController.navigate(BottomNavItem.Home.screenRoute)
                }

                VerticalSpacer(64)
            }
        }
    }
}