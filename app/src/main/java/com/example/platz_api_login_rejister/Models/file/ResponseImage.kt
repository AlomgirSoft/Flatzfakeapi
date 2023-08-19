package com.example.platz_api_login_rejister.Models.file


import com.google.gson.annotations.SerializedName

data class ResponseImage(
    @SerializedName("filename")
    var filename: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("originalname")
    var originalname: String?
)