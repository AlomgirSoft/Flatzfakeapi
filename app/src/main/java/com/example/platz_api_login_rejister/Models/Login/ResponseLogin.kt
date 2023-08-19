package com.example.platz_api_login_rejister.Models.Login


import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("access_token")
    var accessToken: String?,
    @SerializedName("refresh_token")
    var refreshToken: String?
)