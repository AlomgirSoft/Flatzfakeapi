package com.example.platz_api_login_rejister.Models.userProfile


import com.google.gson.annotations.SerializedName

data class ResponseUserProfile(
    @SerializedName("avatar")
    var avatar: String?,
    @SerializedName("creationAt")
    var creationAt: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("password")
    var password: String?,
    @SerializedName("role")
    var role: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?
)