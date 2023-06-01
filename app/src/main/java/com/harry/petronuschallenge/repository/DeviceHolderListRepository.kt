package com.harry.petronuschallenge.repository

import com.harry.petronuschallenge.data.model.DeviceHolder
import com.harry.petronuschallenge.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DeviceHolderListRepository {
    suspend fun getList(): Flow<Resource<DeviceHolder>>
}