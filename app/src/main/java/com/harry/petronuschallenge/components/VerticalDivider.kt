package com.harry.petronuschallenge.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.harry.petronuschallenge.ui.theme.Gray

/**
 * Vertical Divider - Separator.
 */
@Composable
fun VerticalDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)                 // Specify the width of the divider
            .height(20.dp)            // Make the divider fill the available height
            .background(Gray)      // Set the background color of the divider
    )
}