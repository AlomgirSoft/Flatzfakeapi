package com.example.platz_api_login_rejister.Models.repositories

import com.example.platz_api_login_rejister.Models.updateuser.RequestUserUpdate
import com.example.platz_api_login_rejister.Models.updateuser.ResponseUserUpdate
import com.example.platz_api_login_rejister.Models.userProfile.ResponseUserProfile
import com.example.platz_api_login_rejister.Serviecs.AuthServiec
import com.example.platz_api_login_rejister.Serviecs.UserServiec
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path
import javax.inject.Inject


class UserProfileRepo  @Inject constructor(var userProfileRepo: UserServiec) {


   suspend fun getUserProfile():Response<ResponseUserProfile>{
        return userProfileRepo.userProfile()
    }

    suspend fun userUpdate(
    id:Int,
    requset: RequestUserUpdate
    ):Response<ResponseUserUpdate>{

        return userProfileRepo.userUpdate(id, requset)
    }
}