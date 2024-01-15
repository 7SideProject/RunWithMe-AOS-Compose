package com.side.runwithme.ui.screens.join

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.side.runwithme.R
import com.side.runwithme.components.IntListDropDownMenu
import com.side.runwithme.components.MyToolBar
import com.side.runwithme.components.RoundedStrongButton
import com.side.runwithme.components.TextInput
import com.side.runwithme.components.VerticalSpacer
import com.side.runwithme.ui.theme.RunWithMeTheme
import com.side.runwithme.utils.PasswordVerificationType
import com.side.runwithme.utils.matchesNickNameRule
import com.side.runwithme.utils.passwordValidation

@Preview(showBackground = true)
@Composable
fun JoinStep3Preview() {
    val navController = rememberNavController()
    RunWithMeTheme {
        JoinStep3Screen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JoinStep3Screen() {

    val nickname = remember {
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
                text = stringResource(id = R.string.nickname),
                style = MaterialTheme.typography.titleMedium
            )

            VerticalSpacer(16)

            TextInput(
                modifier = Modifier.wrapContentHeight(),
                valueState = nickname,
                labelId = stringResource(id = R.string.nickname),
                enabled = true,
                isSingleLine = true,
                imeAction = ImeAction.Next
            )

            VerticalSpacer(32)

            Text(
                text = stringResource(id = R.string.height),
                style = MaterialTheme.typography.titleMedium
            )

            VerticalSpacer(16)

            HeightDropDownMenu()

            VerticalSpacer(32)

            Text(
                text = stringResource(id = R.string.weight),
                style = MaterialTheme.typography.titleMedium
            )

            VerticalSpacer(16)

            WeightDropDownMenu()

            Spacer(Modifier.weight(1f))

            if(matchesNickNameRule(nickName = nickname.value)) {
                RoundedStrongButton(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.register)
                )
            }
        }
    }
}

@Composable
fun HeightDropDownMenu(){
    val expanded = remember {
        mutableStateOf(false)
    }

    val heightList = Array(131) { i -> i + 120}
    val selectedHeightIndex = remember {
        mutableStateOf(43)
    }

    IntListDropDownMenu(expanded = expanded, list = heightList, selectedIndex = selectedHeightIndex)
}

@Composable
fun WeightDropDownMenu(){
    val expanded = remember {
        mutableStateOf(false)
    }

    val weightList = Array(231) { i -> i + 20 }
    val selectedWeightIndex = remember {
        mutableStateOf(40)
    }

    IntListDropDownMenu(expanded = expanded, list = weightList, selectedIndex = selectedWeightIndex)
}