package com.harry.petronuschallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.harry.petronuschallenge.navigation.Screens
import com.harry.petronuschallenge.navigation.SetUpNavController
import com.harry.petronuschallenge.ui.theme.PetronusChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PetronusChallenge)
        super.onCreate(savedInstanceState)
        setContent {
            PetronusChallengeTheme {
                SetUpNavController(
                    controller = rememberNavController(),
                    startDestination = Screens.DeviceHolderListScreen.route
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetronusChallengeTheme {

    }
}