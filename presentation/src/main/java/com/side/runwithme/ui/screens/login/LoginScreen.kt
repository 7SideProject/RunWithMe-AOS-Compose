package com.side.runwithme.ui.screens.login

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    loginViewModel: LoginViewModel = viewModel()
) {

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
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

                LoginButton(navController = navController, loginViewModel = loginViewModel)

                VerticalSpacer(16)

                JoinButton(navController = navController)
            }
        }
    }
}

@Composable
fun LoginButton(navController: NavController, loginViewModel: LoginViewModel){
    RoundedStrongButton(
        stringResource(id = R.string.login)
    ) {
        navController.navigate(BottomNavItem.Home.screenRoute)
    }
}

@Composable
fun JoinButton(navController: NavController){
    RoundedWhiteButton(
        stringResource(id = R.string.join)
    ) {
        navController.navigate(BottomNavItem.Home.screenRoute)
    }
}

