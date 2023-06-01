package com.harry.petronuschallenge.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harry.petronuschallenge.data.model.DeviceHolder
import com.harry.petronuschallenge.repository.DeviceHolderListRepository
import com.harry.petronuschallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceHolderListViewModel @Inject constructor(
    private val repository: DeviceHolderListRepository
) : ViewModel() {
    private val _resultFlow = MutableStateFlow<Resource<DeviceHolder>>(Resource.Loading)
    val resultFlow: StateFlow<Resource<DeviceHolder>> = _resultFlow

    init {
        getList()
    }

    fun getList() {
        viewModelScope.launch(Dispatchers.Main) {
            repository.getList().flowOn(Dispatchers.IO).catch { e ->
                _resultFlow.value = Resource.Failure(e as Exception)
            }.collect {
                _resultFlow.value = it
            }
        }
    }

}