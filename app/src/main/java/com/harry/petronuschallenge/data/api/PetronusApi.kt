package com.harry.petronuschallenge.data.api

import com.harry.petronuschallenge.data.model.DeviceHolder
import com.harry.petronuschallenge.data.model.DeviceHolderDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PetronusApi {

    @GET("users")
    suspend fun getDeviceHolderList(): Response<DeviceHolder>

    @GET("users/{id}")
    suspend fun getDeviceHolderDetails(@Path(value = "id") id: String): Response<DeviceHolderDetails>
}