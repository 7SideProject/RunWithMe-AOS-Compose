package com.side.runwithme.ui.screens.my_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun MyPageScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "mypage", color = Color.Black)
    }
}