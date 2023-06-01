package com.harry.petronuschallenge.repository

import com.harry.petronuschallenge.data.model.DeviceHolderDetails
import com.harry.petronuschallenge.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DeviceHolderDetailsRepository {
    suspend fun getDetails(id: String): Flow<Resource<DeviceHolderDetails>>
}