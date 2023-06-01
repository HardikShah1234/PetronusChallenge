package com.harry.petronuschallenge.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.harry.petronuschallenge.ui.theme.BackgroundPinkShade
import com.harry.petronuschallenge.ui.theme.GrayShade
import com.harry.petronuschallenge.ui.theme.Typography


@Composable
fun DisplayStickers(
    item: List<String>,
    padding: Dp = 0.dp,
    startPadding: Dp = 0.dp,
    endPadding: Dp = 0.dp
) {

    Row(horizontalArrangement = Arrangement.Start) {
        item.filter {
            it.isNotEmpty()
        }.forEach { sticker ->
            val backgroundColor = if (sticker == "Fam") GrayShade else BackgroundPinkShade

            Box(
                modifier = Modifier
                    .padding(end = padding)
                    .background(backgroundColor, shape = RoundedCornerShape(4.dp))
                    .padding(start = startPadding, end = endPadding)
            ) {
                Text(
                    text = sticker,
                    style = if (sticker == "Fam") Typography.subtitle2 else Typography.subtitle1
                )
            }
        }
    }

}