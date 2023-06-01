package com.harry.petronuschallenge.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp


@Composable
fun OvalShape() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radiusX = size.width / 2
        val radiusY = size.height / 2

        drawOval(
            color = Color.Blue,
            topLeft = Offset(centerX - radiusX, centerY - radiusY),
            size = Size(radiusX * 2, radiusY * 2),
            style = Stroke(width = 2.dp.toPx())
        )
    }
}

