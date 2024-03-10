package com.side.runwithme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.side.runwithme.MainActivity.Companion.loadingState
import com.side.runwithme.designsystem.theme.RunWithMeTheme
import com.side.runwithme.navigation.BottomNavItem
import com.side.runwithme.navigation.RunWithMeScreens
import com.side.runwithme.ui.screens.home.HomeScreen
import com.side.runwithme.ui.screens.join.JoinStep1Screen
import com.side.runwithme.ui.screens.join.JoinStep2Screen
import com.side.runwithme.ui.screens.join.JoinStep3Screen
import com.side.runwithme.ui.screens.login.LoginScreen
import com.side.runwithme.ui.screens.my_page.MyPageScreen
import com.side.runwithme.ui.screens.running_list.RunningListScreen
import dagger.hilt.android.AndroidEntryPoint

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    RunWithMeTheme {
        MyApp()
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        var loadingState = mutableStateOf(
            false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunWithMeTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {

    val navController = rememberNavController()

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            RunWithMeBottomNaivgation(navController, bottomBarState)
        },
        floatingActionButton = {
            if(bottomBarState.value) {
                RunningListFloatingButton(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->

        /** 로딩 다이얼로그 */
        if (loadingState.value) {
            LoadingDialog()
        }

        NavHost(
            navController = navController,
            startDestination = RunWithMeScreens.LoginScreen.name,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {

            composable(BottomNavItem.Home.screenRoute) {
                bottomBarState.value = true
                HomeScreen(navController)
            }

            composable(BottomNavItem.RunningList.screenRoute) {
                bottomBarState.value = true
                RunningListScreen(navController)
            }

            composable(BottomNavItem.MyPage.screenRoute) {
                bottomBarState.value = true
                MyPageScreen(navController)
            }

            composable(RunWithMeScreens.LoginScreen.name) {
                bottomBarState.value = false
//                val viewModel = hiltViewModel<LoginViewModel>()
                LoginScreen(navController)
            }

            composable(RunWithMeScreens.JoinScreen1.name) {
                bottomBarState.value = false
                JoinStep1Screen(navController)
            }

            composable(RunWithMeScreens.JoinScreen2.name) {
                bottomBarState.value = false
                JoinStep2Screen(navController)
            }

            composable(RunWithMeScreens.JoinScreen3.name) {
                bottomBarState.value = false
                JoinStep3Screen(navController)
            }
        }
    }
}

@Composable
fun RunWithMeBottomNaivgation(
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    val bottomItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.RunningList,
        BottomNavItem.MyPage
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            NavigationBar {
                bottomItems.forEachIndexed { index, bottomItem ->
                    NavigationBarItem(
                        selected = currentRoute == bottomItem.screenRoute,
                        onClick = {
                            if (navController.currentBackStackEntry?.destination?.route == bottomItem.screenRoute) return@NavigationBarItem

                            navController.navigate(bottomItem.screenRoute) {
                                navController.popBackStack(
                                    route = BottomNavItem.Home.screenRoute,
                                    inclusive = false
                                )
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = bottomItem.icon),
                                contentDescription = stringResource(id = bottomItem.title),
                                modifier = Modifier.size(32.dp)
                            )
                        })
                }
            }
        }
    )
}

/** 로딩 다이얼로그 */
@Composable
private fun LoadingDialog() {
    Dialog(
        onDismissRequest = { loadingState.value = false },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .background(Color.White, shape = CircleShape)
        ) {
            val context = LocalContext.current
            val composition by rememberLottieComposition(LottieCompositionSpec.Asset("loading.json"))
            val logoAnimationState =
                animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
            LottieAnimation(
                modifier = Modifier.align(Alignment.Center),
                composition = composition,
                progress = { logoAnimationState.progress }
            )
        }
    }
}

fun startLoading() {
    loadingState.value = true
}

fun stopLoading() {
    loadingState.value = false
}

@Composable
fun RunningListFloatingButton(navController: NavController) {
    Box(){
        FloatingActionButton(
            onClick = {
                navController.navigate(BottomNavItem.RunningList.screenRoute)
            },
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.Center)
                .size(90.dp)
                .offset(y = 60.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(56.dp),
                painter = painterResource(id = R.drawable.run),
                tint = Color.White,
                contentDescription = stringResource(id = R.string.running_list)
            )
        }
    }
}

@Composable
fun NavBackStackEntry.sharedViewModel(navController: NavController): ViewModel {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}