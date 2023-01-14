package com.keepcoding.androidavanzado.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keepcoding.androidavanzado.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> get() = _token


    fun login(){
        viewModelScope.launch {
            val userToken = withContext(Dispatchers.IO){
                repository.login()
            }
            _token.value = userToken
        }
    }
}