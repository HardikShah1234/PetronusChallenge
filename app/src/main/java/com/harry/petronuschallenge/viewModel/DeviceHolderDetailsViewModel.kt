package com.harry.petronuschallenge.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harry.petronuschallenge.data.model.DeviceHolderDetails
import com.harry.petronuschallenge.repository.DeviceHolderDetailsRepository
import com.harry.petronuschallenge.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for fetching the details of the user.
 */
@HiltViewModel
class DeviceHolderDetailsViewModel @Inject constructor(
    private val repository: DeviceHolderDetailsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _detailsFlow = MutableStateFlow<Resource<DeviceHolderDetails>?>(null)
    val detailsFlow: StateFlow<Resource<DeviceHolderDetails>?> = _detailsFlow

    private val id: String? = savedStateHandle.get("Id")

    init {
        getDetails(id)
    }

    private fun getDetails(id: String? = null) {
        viewModelScope.launch {
            _detailsFlow.update { Resource.Loading }
            val response = id?.let { repository.getDetails(it) }
            _detailsFlow.update { response?.firstOrNull() }
        }
    }
}