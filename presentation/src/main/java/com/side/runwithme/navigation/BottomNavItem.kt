package com.side.runwithme.navigation

import com.side.runwithme.R

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Home : BottomNavItem(R.string.home, R.drawable.home, "HOME")
    object RunningList : BottomNavItem(R.string.running_list, R.drawable.transparent, "RUNNING_LIST")
    object MyPage : BottomNavItem(R.string.my_page, R.drawable.my_page, "MY_PAGE")
}