package com.side.runwithme.ui.screens.join

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.side.runwithme.R
import com.side.runwithme.components.MyToolBar
import com.side.runwithme.components.PasswordInput
import com.side.runwithme.components.RoundedStrongButton
import com.side.runwithme.components.VerticalSpacer
import com.side.runwithme.navigation.RunWithMeScreens
import com.side.runwithme.ui.theme.RunWithMeTheme
import com.side.runwithme.utils.PasswordVerificationType
import com.side.runwithme.utils.passwordValidation

@Preview(showBackground = true)
@Composable
fun JoinStep2Preview() {
    val navController = rememberNavController()
    RunWithMeTheme {
        JoinStep2Screen(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinStep2Screen(navController: NavController) {

    val password = remember {
        mutableStateOf("")
    }

    val passwordConfirm = remember {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            MyToolBar(title = stringResource(id = R.string.join),
                onBackClick = {
                    navController.navigateUp()
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 500.dp)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 20.dp,
                    end = 20.dp
                )
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(160.dp),
                painter = painterResource(id = R.drawable.login_logo2),
                contentDescription = "RunWithMeLogo"
            )

            VerticalSpacer(16)

            Text(
                text = stringResource(id = R.string.join_title2),
                style = MaterialTheme.typography.titleLarge
            )

            VerticalSpacer(16)

            Text(
                text = stringResource(id = R.string.join_password_title),
                style = MaterialTheme.typography.titleMedium
            )

            VerticalSpacer(16)

            PasswordInput(
                passwordState = password,
                labelId = stringResource(id = R.string.password),
                enabled = true
            )

            VerticalSpacer(16)

            PasswordInput(
                passwordState = passwordConfirm,
                labelId = stringResource(id = R.string.password_confirm),
                enabled = true
            )

            Spacer(Modifier.weight(1f))

//            if (passwordValidation(
//                    password.value,
//                    passwordConfirm.value
//                ) == PasswordVerificationType.SUCCESS
//            ) {
                OutlinedButton(
                    onClick = { navController.navigate(RunWithMeScreens.JoinScreen3.name) }) {
                    Text(text = stringResource(id = R.string.next))
                }
//            }
        }
    }
}