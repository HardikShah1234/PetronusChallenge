package com.harry.petronuschallenge.navigation

sealed class Screens(val route: String) {
    object DeviceHolderListScreen : Screens("DeviceHolderList")
    object DeviceHolderDetailsScreen : Screens("DeviceHolderDetailsScreen/{Id}")
}
