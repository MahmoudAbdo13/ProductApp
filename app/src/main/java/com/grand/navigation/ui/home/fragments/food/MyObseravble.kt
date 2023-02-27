package com.grand.navigation.ui.home.fragments.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyObseravble :  ViewModel()
{
    var data = MutableLiveData<String>()

    fun data(item: String) {
        data.value = item
    }
}