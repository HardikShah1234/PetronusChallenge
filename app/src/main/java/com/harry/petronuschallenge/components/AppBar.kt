package com.harry.petronuschallenge.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.harry.petronuschallenge.R
import com.harry.petronuschallenge.ui.theme.Blue
import com.harry.petronuschallenge.ui.theme.Typography
import com.harry.petronuschallenge.ui.theme.White

/**
 * Reusable app bar which is used in every screen as per design.
 */
@Composable
fun AppBar(
    modifier: Modifier,
    title: String = "",
    showBackArrow: Boolean = false,
    onBackClicked: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = White,
        contentColor = Blue,
        elevation = 0.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (showBackArrow) {
                IconButton(onClick = onBackClicked) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back"
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(24.dp))
            }

            Text(
                text = title,
                style = Typography.h1,
                textAlign = TextAlign.Start,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
        }
    }
}