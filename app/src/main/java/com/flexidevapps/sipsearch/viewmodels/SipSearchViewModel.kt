package com.flexidevapps.sipsearch.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class SipSearchViewModel(
    private val color: Color

): ViewModel() {

    var backgroundColor by mutableStateOf(Color.White)
        private set

    fun changeBackGroundColor() {
        backgroundColor = if(backgroundColor == Color.White) color else Color.White

    }


}