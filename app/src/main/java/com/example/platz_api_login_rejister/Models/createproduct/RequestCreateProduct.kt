package com.example.platz_api_login_rejister.Models.createproduct


import com.google.gson.annotations.SerializedName

data class RequestCreateProduct(
    @SerializedName("categoryId")
    var categoryId: Int?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("images")
    var images: List<String?>?,
    @SerializedName("price")
    var price: String?,
    @SerializedName("title")
    var title: String?
)