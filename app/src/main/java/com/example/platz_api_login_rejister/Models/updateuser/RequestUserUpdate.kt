package com.example.platz_api_login_rejister.Models.updateuser


import com.google.gson.annotations.SerializedName

data class RequestUserUpdate(
    @SerializedName("email")
    var email: String?,
    @SerializedName("name")
    var name: String?
)