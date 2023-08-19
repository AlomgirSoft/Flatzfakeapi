package com.example.platz_api_login_rejister.Views.Fragment.registerViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platz_api_login_rejister.Models.Register.RequestRegister
import com.example.platz_api_login_rejister.Models.Register.ResponseRegister
import com.example.platz_api_login_rejister.Models.repositories.AuthRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepo:AuthRepositories):ViewModel(){
    private val _response=MutableLiveData<Response<ResponseRegister>>()
    val responseRegister:LiveData<Response<ResponseRegister>> = _response



    fun register(requestRegister: RequestRegister){
        viewModelScope.launch {

           val data=authRepo.register(requestRegister)
            _response.postValue(data)
        }

    }


}