package com.harry.petronuschallenge.data.model


import com.google.gson.annotations.SerializedName

data class DeviceHolderDetails(
    @SerializedName("address")
    val address: Address,
    @SerializedName("currentLatitude")
    val currentLatitude: Double,
    @SerializedName("currentLongitude")
    val currentLongitude: Double,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("stickers")
    val stickers: List<String>
)