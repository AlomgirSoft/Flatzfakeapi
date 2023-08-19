package com.example.platz_api_login_rejister.Models.repositories

import com.example.platz_api_login_rejister.Models.Login.RequestLogin
import com.example.platz_api_login_rejister.Models.Login.ResponseLogin
import com.example.platz_api_login_rejister.Models.Register.RequestRegister
import com.example.platz_api_login_rejister.Models.Register.ResponseRegister
import com.example.platz_api_login_rejister.Serviecs.AuthServiec
import retrofit2.Response
import javax.inject.Inject

class AuthRepositories @Inject constructor(val serviec: AuthServiec){



   suspend fun login(request:RequestLogin) : Response<ResponseLogin> {

       return serviec.login(request)

    }

   suspend fun register(requestRegister: RequestRegister):Response<ResponseRegister>{

        return serviec.register(requestRegister)
    }





}