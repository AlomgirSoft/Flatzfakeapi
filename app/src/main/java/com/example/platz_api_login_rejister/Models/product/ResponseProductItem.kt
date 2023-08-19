package com.example.platz_api_login_rejister.Models.product


import com.google.gson.annotations.SerializedName

data class ResponseProductItem(
    @SerializedName("category")
    var category: Category?,
    @SerializedName("creationAt")
    var creationAt: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: Int,
    @SerializedName("images")
    var images: List<String?>?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?
)