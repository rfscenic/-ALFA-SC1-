package com.alfa_sc1.mastur.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "https://indonesia.tripcanvas.co/id/wp-content/uploads/sites/2/2020/03/13-1-Bukit-Sianco-by-dwiecahyo-740x555.jpg"
    }
    val text: LiveData<String> = _text
}