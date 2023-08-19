package com.example.platz_api_login_rejister.Models.Login


import com.google.gson.annotations.SerializedName

data class  RequestLogin(
    @SerializedName("email")
    var email: String?,
    @SerializedName("password")
    var password: String?
)