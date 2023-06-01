package com.harry.petronuschallenge.data.model


import com.google.gson.annotations.SerializedName

data class DeviceHolder(
    @SerializedName("customers")
    val customers: List<Customer>
)