package com.side.runwithme.ui.screens.join

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.side.runwithme.components.MyToolBar
import com.side.runwithme.components.RoundedWeakButton
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
@OptIn(ExperimentalMaterial3Api::class)
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
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.email),
                style = MaterialTheme.typography.titleMedium
            )

            VerticalSpacer(16)

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.verify_number),
                    style = MaterialTheme.typography.titleMedium
                )

                EmailInput(modifier = Modifier.weight(2f),
                    emailState = email,
                    imeAction = ImeAction.Done,
                    onAction = KeyboardActions(onDone = {
                        isClickSendButton.value = true
                    }))

                RoundedWeakButton(modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(), label = stringResource(id = R.string.send_verify_number)
                ) {
                    isClickSendButton.value = true
                }

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