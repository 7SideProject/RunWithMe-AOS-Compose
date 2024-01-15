package com.side.runwithme.utils

enum class ResponseCodeStatus(val code: Int) {
    // User
    LOGIN_SUCCESS      			    (100),
    NOT_EXIST_USER                  (-101),
    WRONG_PASSWORD                  (-107),

}