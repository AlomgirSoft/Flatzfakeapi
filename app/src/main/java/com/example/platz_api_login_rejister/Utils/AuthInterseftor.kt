package com.example.platz_api_login_rejister.Utils

import com.example.platz_api_login_rejister.Constens.KAY_ACCESS_TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterseftor @Inject constructor(var prefManeger: PrefManeger):Interceptor {



    override fun intercept(chain: Interceptor.Chain): Response {

        val request=  chain.request().newBuilder()

             request.addHeader("Authorization", "Bearer ${prefManeger.getPref(KAY_ACCESS_TOKEN)}")
     return chain.proceed(request.build())

    }

}