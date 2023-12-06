package com.side.runwithme.ui.screens.home

import com.side.runwithme.R

sealed class HomeMenus(
    val title: Int, val icon: Int, val screenRoute: String? = null
) {
    /** screenRoute 변경 필요 **/
    object DailyCheck: HomeMenus(R.string.daily_check, R.drawable.daily_check)
    object MyChallengeList: HomeMenus(R.string.my_challenge_list, R.drawable.my_running, "")
    object RecruitChallengeList: HomeMenus(R.string.recruit_challenge_list, R.drawable.running_challenge, "")
}