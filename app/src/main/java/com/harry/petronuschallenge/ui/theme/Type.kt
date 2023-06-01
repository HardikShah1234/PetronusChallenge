package com.harry.petronuschallenge.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = rubikFont,
        fontWeight = FontWeight.Bold,
        fontSize = 27.sp
    ),
    h2 = TextStyle(
        fontFamily = rubikFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = LightBlueShade
    ),
    h3 = TextStyle(
        fontFamily = rubikFont,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 20.sp,
        color = LightBlue
    ),
    h4 = TextStyle(
        fontFamily = rubikFont,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        color = LightBlue,
        lineHeight = 18.sp
    ),
    body1 = TextStyle(
        fontFamily = rubikFont,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        color = LightBlueShade
    ),
    body2 = TextStyle(
        fontFamily = rubikFont,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp,
        color = Blue
    ),
    subtitle1 = TextStyle(
        fontFamily = rubikFont,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = PinkShade
    ),
    subtitle2 = TextStyle(
        fontFamily = rubikFont,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        color = LightBlueShade
    )
)