package com.harry.petronuschallenge.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harry.petronuschallenge.ui.theme.LightBlue

@Composable
fun LoadingIndicator() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = LightBlue
        )
    }
}