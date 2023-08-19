package com.example.platz_api_login_rejister.Serviecs

import com.example.platz_api_login_rejister.Constens.AUTH_EIN_POINT
import com.example.platz_api_login_rejister.Constens.REGISTER_EID_POINT
import com.example.platz_api_login_rejister.Constens.USER_PROFILE_EID_POINT
import com.example.platz_api_login_rejister.Models.Login.RequestLogin
import com.example.platz_api_login_rejister.Models.Login.ResponseLogin
import com.example.platz_api_login_rejister.Models.Register.RequestRegister
import com.example.platz_api_login_rejister.Models.Register.ResponseRegister
import com.example.platz_api_login_rejister.Models.userProfile.ResponseUserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthServiec {

    @POST(AUTH_EIN_POINT)
  suspend  fun login( @Body request: RequestLogin):Response<ResponseLogin>
  @POST(REGISTER_EID_POINT)
  suspend fun register(@Body requestRegister:RequestRegister):Response<ResponseRegister>
  @GET(USER_PROFILE_EID_POINT)
  suspend fun userProfile():Response<ResponseUserProfile>



}