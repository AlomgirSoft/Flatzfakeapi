package com.example.platz_api_login_rejister.Views.Fragment.LoginViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platz_api_login_rejister.Models.Login.RequestLogin
import com.example.platz_api_login_rejister.Models.Login.ResponseLogin
import com.example.platz_api_login_rejister.Models.repositories.AuthRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor( val authRepo:AuthRepositories):ViewModel() {
  private val  _response=MutableLiveData<Response<ResponseLogin>>()
    val responseLogin:LiveData<Response<ResponseLogin>> =_response


    fun login (request:RequestLogin){

        viewModelScope.launch {
         val response=  authRepo.login(request)
            _response.postValue(response)
        }



    }

}