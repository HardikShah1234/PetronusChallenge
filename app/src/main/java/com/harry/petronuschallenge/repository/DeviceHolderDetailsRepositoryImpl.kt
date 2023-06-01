package com.harry.petronuschallenge.repository

import com.harry.petronuschallenge.data.api.PetronusApi
import com.harry.petronuschallenge.data.model.DeviceHolderDetails
import com.harry.petronuschallenge.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeviceHolderDetailsRepositoryImpl @Inject constructor(
    private val petronusApi: PetronusApi
) : DeviceHolderDetailsRepository {
    override suspend fun getDetails(id: String): Flow<Resource<DeviceHolderDetails>> =
        flow<Resource<DeviceHolderDetails>> {
            val response = petronusApi.getDeviceHolderDetails(id)
            val responseBody = response.body()
            if (responseBody != null) {
                emit(Resource.Success(responseBody))
            }
        }.catch {
            emit(Resource.Failure(it as Exception))
        }.flowOn(Dispatchers.IO)
}