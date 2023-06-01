package com.harry.petronuschallenge.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.harry.petronuschallenge.ui.theme.BackgroundWhiteShade
import com.harry.petronuschallenge.ui.theme.Typography

/**
 * This composable is responsible for loading the image from url fetched from api.
 * If url is null or not having image then first initials of the First name and Last name will be displayed.
 */
@Composable
fun LoadImage(
    url: String?,
    firstLetter: Char,
    lastLetter: Char,
    circleSize: Dp = 48.dp,
    padding: Dp = 0.dp
) {
    val imageState = remember { mutableStateOf(url) }

    val circularShape = Modifier
        .size(circleSize)
        .clip(CircleShape)

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(imageState.value)
            .size(Size.ORIGINAL).build()
    )
    Box(
        modifier = Modifier
            .padding(start = padding)
            .width(48.dp)
            .height(48.dp)
    ) {
        if (painter.state is AsyncImagePainter.State.Success) {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = circularShape
            )
        } else {
            Box(
                modifier = circularShape
                    .background(BackgroundWhiteShade)
            ) {
                Text(
                    text = "${firstLetter.uppercase()}${lastLetter.uppercase()}",
                    style = Typography.h2,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(IntrinsicSize.Max)
                )
            }
        }
    }
}