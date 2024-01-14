package com.side.runwithme.ui.screens.join

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.side.runwithme.R
import com.side.runwithme.components.EmailInput
import com.side.runwithme.components.MyToolBar
import com.side.runwithme.components.RoundedWeakButton
import com.side.runwithme.components.TextInput
import com.side.runwithme.components.VerticalSpacer
import com.side.runwithme.ui.theme.RunWithMeTheme

@Preview(showBackground = true)
@Composable
fun JoinStep1Preview() {
    val navController = rememberNavController()
    RunWithMeTheme {
        JoinStep1Screen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinStep1Screen() {

    val email = rememberSaveable {
        mutableStateOf("")
    }

    val verifyNumber = rememberSaveable {
        mutableStateOf("")
    }

    val isClickSendButton = rememberSaveable {
        mutableStateOf(false)
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
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.titleMedium
            )

            VerticalSpacer(16)

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                EmailInput(modifier = Modifier.weight(2f),
                    emailState = email,
                    imeAction = ImeAction.Done,
                    onAction = KeyboardActions(onDone = {
                        isClickSendButton.value = true
                    }))

                RoundedWeakButton(modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(), label = stringResource(id = R.string.send_verify_number))

            }

            VerticalSpacer(32)

            if(isClickSendButton.value) {
                Text(
                    text = stringResource(id = R.string.verify_number),
                    style = MaterialTheme.typography.titleMedium
                )

                VerticalSpacer(16)

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    TextInput(
                        modifier = Modifier.weight(2f),
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

                    RoundedWeakButton(
                        modifier = Modifier.weight(1f),
                        label = stringResource(id = R.string.verify)
                    )

                }
            }
        }

    }

}