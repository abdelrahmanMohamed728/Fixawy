package com.example.base

import android.text.TextUtils

class TextValidator {
    fun isEmailValid(email : String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}