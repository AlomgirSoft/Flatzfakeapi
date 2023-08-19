package com.example.platz_api_login_rejister.Models.update


import com.google.gson.annotations.SerializedName

data class ResponseUpdate(
    @SerializedName("message")
    var message: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("path")
    var path: String?,
    @SerializedName("timestamp")
    var timestamp: String?
)