package com.bhaskar.streetlight.test1.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel2 : ViewModel() {
    var count = MutableLiveData(0)

    fun increment() {
        count.value = count.value?.inc()
    }
}