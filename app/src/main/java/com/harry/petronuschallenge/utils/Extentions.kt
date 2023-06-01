package com.harry.petronuschallenge.utils

import android.content.res.Resources
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Converts from px to sp.
 * As in figma design font size defined in px.
 */
fun Int.pxToSp(): TextUnit = (this / Resources.getSystem().displayMetrics.scaledDensity).sp