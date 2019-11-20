package qc.ca.cstj.yannickbray.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenue chez YannickBray"
    }
    val text: LiveData<String> = _text
}