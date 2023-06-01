package com.harry.petronuschallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.harry.petronuschallenge.views.DeviceHolderDetailsScreen
import com.harry.petronuschallenge.views.DeviceHolderListScreen

@Composable
fun SetUpNavController(
    controller: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = controller,
        startDestination = startDestination
    ) {
        composable(Screens.DeviceHolderListScreen.route) {
            DeviceHolderListScreen(navController = controller)
        }

        composable("${Screens.DeviceHolderDetailsScreen.route}/Id={Id}") {
            val id = it.arguments?.getString("Id")
            DeviceHolderDetailsScreen(navController = controller, id = id)
        }
    }
}
