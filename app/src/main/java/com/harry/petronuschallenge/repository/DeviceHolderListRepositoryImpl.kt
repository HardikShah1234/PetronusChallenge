package com.harry.petronuschallenge.repository

import com.harry.petronuschallenge.data.api.PetronusApi
import com.harry.petronuschallenge.data.model.DeviceHolder
import com.harry.petronuschallenge.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeviceHolderListRepositoryImpl @Inject constructor(
    private val petronusApi: PetronusApi
) : DeviceHolderListRepository {
    override suspend fun getList(): Flow<Resource<DeviceHolder>> = flow {
        val response = petronusApi.getDeviceHolderList()
        emit(Resource.Success(result = response.body()!!))
    }


}