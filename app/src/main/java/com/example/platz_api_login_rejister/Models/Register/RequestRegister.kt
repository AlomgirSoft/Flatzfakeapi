package com.example.platz_api_login_rejister.Models.Register


import com.google.gson.annotations.SerializedName

data class RequestRegister(
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("password")
    var password: String?
)