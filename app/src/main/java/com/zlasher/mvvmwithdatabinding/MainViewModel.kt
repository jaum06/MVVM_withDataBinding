package com.zlasher.mvvmwithdatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _progressVisibility = MutableLiveData<Boolean>()
    val progressVisibility: LiveData<Boolean> get() = _progressVisibility

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _messageVisibility = MutableLiveData<Boolean>()
    val messageVisibility: LiveData<Boolean> get() = _messageVisibility

    fun onButtonClicked(user: String, pass: String) {
        viewModelScope.launch {
            _progressVisibility.value = true
            _messageVisibility.value = false
            _message.value = withContext(Dispatchers.IO) {
                Thread.sleep(2000)
                if (user.isNotEmpty() && pass.isNotEmpty()) "Success" else "Failure"
            }
            _progressVisibility.value = false
            _messageVisibility.value = true
        }
    }
}