package com.grand.navigation.ui.onBoarding

import android.content.Context
import android.content.SharedPreferences

class FirstUsing(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("FirstUsing",Context.MODE_PRIVATE)

    fun setAppUsed(used: Boolean){
        val editor:SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putBoolean("used", used)
        editor.apply()
    }

    fun getAppUsed(): Boolean {
         return sharedPreferences.getBoolean("used", true)
    }
}