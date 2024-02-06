package com.side.runwithme.ui.screens.join

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.side.runwithme.R
import com.side.runwithme.components.EmailInput
import com.side.runwithme.components.HorizontalSpacer
import com.side.runwithme.components.MyToolBar
import com.side.runwithme.components.TextInput
import com.side.runwithme.components.VerticalSpacer
import com.side.runwithme.navigation.RunWithMeScreens
import com.side.runwithme.ui.theme.RunWithMeTheme

@Preview(showBackground = true)
@Composable
fun JoinStep1Preview() {
    val navController = rememberNavController()
    RunWithMeTheme {
        JoinStep1Screen(navController)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun JoinStep1Screen(
    navController: NavController,
) {

    val email = rememberSaveable {
        mutableStateOf("")
    }

    val verifyNumber = rememberSaveable {
        mutableStateOf("")
    }

    val isClickSendButton = rememberSaveable {
        mutableStateOf(false)
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
        content = { innerPadding ->
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
                    text = stringResource(id = R.string.join_title),
                    style = MaterialTheme.typography.titleMedium
                )

                VerticalSpacer(16)

                Text(
                    text = stringResource(id = R.string.join_email_title),
                    style = MaterialTheme.typography.titleSmall
                )
                VerticalSpacer(16)
                Column(
                    Modifier.widthIn(max = 500.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    EmailInput(
                        emailState = email,
                        imeAction = ImeAction.Done,
                        onAction = KeyboardActions(onDone = {
                            isClickSendButton.value = true
                        })
                    )

                    VerticalSpacer(16)

                    OutlinedButton(onClick = { isClickSendButton.value = true }) {
                        Text(text = stringResource(id = R.string.send_verify_number))
                    }

                    VerticalSpacer(32)

                    if (isClickSendButton.value) {
                        Text(
                            text = stringResource(id = R.string.verify_number),
                            style = MaterialTheme.typography.titleSmall
                        )

                        VerticalSpacer(16)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextInput(
                                modifier = Modifier.weight(1f),
                                valueState = verifyNumber,
                                labelId = stringResource(id = R.string.verify),
                                enabled = true,
                                isSingleLine = true,
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done,
                                onAction = KeyboardActions(onDone = {
                                    // 인증하기
                                })
                            )

                            HorizontalSpacer(16)

                            OutlinedButton(
                                onClick = { navController.navigate(RunWithMeScreens.JoinScreen2.name) }) {
                                Text(text = stringResource(id = R.string.verify))
                            }
                        }
                    }
                }
            }
        }
    )

}