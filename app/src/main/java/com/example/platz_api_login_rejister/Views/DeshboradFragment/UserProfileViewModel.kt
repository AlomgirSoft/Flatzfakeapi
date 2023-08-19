package com.example.platz_api_login_rejister.Views.DeshboradFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.platz_api_login_rejister.Models.repositories.UserProfileRepo
import com.example.platz_api_login_rejister.Models.updateuser.RequestUserUpdate
import com.example.platz_api_login_rejister.Models.updateuser.ResponseUserUpdate
import com.example.platz_api_login_rejister.Models.userProfile.ResponseUserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class UserProfileViewModel @Inject constructor(private val userProfileRepo:UserProfileRepo): ViewModel() {

  private  val response=MutableLiveData<Response<ResponseUserProfile>>()

    val userProfileResponse:LiveData<Response<ResponseUserProfile>>  =  response

         private var  userId = -1
    fun userProfile(){
        viewModelScope.launch {
          var data= userProfileRepo.getUserProfile()
            response.postValue(data)

            if (data.isSuccessful){

                userId = userProfileRepo.getUserProfile().body()?.id!!
            }
        }




    }


    private  val _responseUserUpdate=MutableLiveData<Response<ResponseUserUpdate>>()

    val responseUserUpdate:LiveData<Response<ResponseUserUpdate>>  =  _responseUserUpdate
   fun userUpdate(  requset: RequestUserUpdate )
    {
        viewModelScope.launch {
      val date =userProfileRepo.userUpdate(userId, requset)
            _responseUserUpdate.postValue(date)

          }

    }

}