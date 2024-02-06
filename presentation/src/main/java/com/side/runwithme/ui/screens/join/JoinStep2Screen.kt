package com.side.runwithme.ui.screens.join

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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

    Scaffold(
        topBar = {
            MyToolBar(title = stringResource(id = R.string.join),
                onBackClick = {
                    // 뒤로가기
                })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(top = 64.dp)
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = stringResource(id = R.string.password),
                style = MaterialTheme.typography.titleMedium
            )

            VerticalSpacer(16)

            PasswordInput(passwordState = password, labelId = stringResource(id = R.string.password), enabled = true)

            VerticalSpacer(32)

            Text(
                text = stringResource(id = R.string.password_confirm),
                style = MaterialTheme.typography.titleMedium
            )

            VerticalSpacer(16)

            PasswordInput(passwordState = passwordConfirm, labelId = stringResource(id = R.string.password_confirm), enabled = true)

            Spacer(Modifier.weight(1f))

            if(passwordValidation(password.value, passwordConfirm.value) == PasswordVerificationType.SUCCESS) {
                RoundedStrongButton(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.next)
                )
            }
        }
    }
}