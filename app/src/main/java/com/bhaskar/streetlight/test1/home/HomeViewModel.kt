package com.bhaskar.streetlight.test1.home

import androidx.lifecycle.ViewModel

class HomeViewModel(value: Int) : ViewModel() {
    var count = value

    fun increment() {
        count++
    }
}