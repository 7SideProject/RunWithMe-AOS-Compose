package com.side.runwithme.ui.screens.join

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.side.runwithme.R
import com.side.runwithme.designsystem.components.IntListDropDownMenu
import com.side.runwithme.designsystem.components.MyToolBar
import com.side.runwithme.designsystem.components.TextInput
import com.side.runwithme.designsystem.components.VerticalSpacer
import com.side.runwithme.designsystem.theme.RunWithMeTheme
import com.side.runwithme.navigation.RunWithMeScreens
import com.side.runwithme.utils.matchesNickNameRule

@Preview(showBackground = true)
@Composable
fun JoinStep3Preview() {
    val navController = rememberNavController()
    RunWithMeTheme {
        JoinStep3Screen(navController)
    }
}

@Composable
fun JoinStep3Screen(navController: NavController) {

    val nickname = remember {
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
                text = stringResource(id = R.string.join_title3),
                style = MaterialTheme.typography.titleLarge
            )

            VerticalSpacer(16)

            Text(
                text = stringResource(id = R.string.join_detail_title),
                style = MaterialTheme.typography.titleMedium
            )

            VerticalSpacer(16)

            TextInput(
                valueState = nickname,
                labelId = stringResource(id = R.string.nickname),
                enabled = true,
                isSingleLine = true,
                imeAction = ImeAction.Next
            )

            VerticalSpacer(16)

            HeightDropDownMenu()

            VerticalSpacer(16)

            WeightDropDownMenu()

            Spacer(Modifier.weight(1f))

            if (matchesNickNameRule(nickName = nickname.value)) {
                OutlinedButton(
                    onClick = { navController.navigate(RunWithMeScreens.LoginScreen.name) }) {
                    Text(text = stringResource(id = R.string.register))
                }
            }
        }
    }
}

@Composable
fun HeightDropDownMenu() {
    val expanded = remember {
        mutableStateOf(false)
    }

    val heightList = Array(131) { i -> i + 120 }
    val selectedHeightIndex = remember {
        mutableStateOf(43)
    }

    IntListDropDownMenu(
        expanded = expanded,
        list = heightList,
        selectedIndex = selectedHeightIndex
    )
}

@Composable
fun WeightDropDownMenu() {
    val expanded = remember {
        mutableStateOf(false)
    }

    val weightList = Array(231) { i -> i + 20 }
    val selectedWeightIndex = remember {
        mutableStateOf(40)
    }

    IntListDropDownMenu(
        expanded = expanded,
        list = weightList,
        selectedIndex = selectedWeightIndex
    )
}