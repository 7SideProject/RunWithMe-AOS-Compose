package com.side.runwithme.ui.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.side.runwithme.R
import com.side.runwithme.ui.screens.login.LoginScreen
import com.side.runwithme.designsystem.theme.Black
import com.side.runwithme.designsystem.theme.MainBlack
import com.side.runwithme.designsystem.theme.RunWithMeTheme

@Preview(showBackground = true)
@Composable
fun HomePreview(){
    val navController = rememberNavController()
    com.side.runwithme.designsystem.theme.RunWithMeTheme {
        HomeScreen(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val homeMenus = listOf(
        HomeMenus.DailyCheck,
        HomeMenus.MyChallengeList,
        HomeMenus.RecruitChallengeList
    )
    val scrollState = rememberScrollState()


    Scaffold(
        topBar = {
            HomeToolBar()
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()) {
                Text(text = "home", color = Color.Black)
            }

            homeMenus.forEach { menu ->
                CardViewScreen(icon = menu.icon, text = stringResource(id = menu.title))

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolBar() {
    TopAppBar(
        modifier = Modifier.shadow(6.dp),
        title = {
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.rwm_logo),
                contentDescription = "RunWithMe",
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors()
    )
}

@Composable
fun CardViewScreen(
    icon: Int,
    text: String,
    onClick: () -> Unit = {}
) {
    val cardRoundedShpae = RoundedCornerShape(corner = CornerSize(12.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(shape = cardRoundedShpae)
            .border(width = 1.dp, color = com.side.runwithme.designsystem.theme.MainBlack, shape = cardRoundedShpae)
            .clickable {
                onClick.invoke()
            }
    ) {

        Image(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight(),
            painter = painterResource(id = icon),
            contentDescription = text
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                contentDescription = text
            )

        }

        Spacer(modifier = Modifier.height(12.dp))

    }
}