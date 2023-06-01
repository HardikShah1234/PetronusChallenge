package com.harry.petronuschallenge.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.graphics.PathParser
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.harry.petronuschallenge.R
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import java.util.regex.Pattern

/**
 * Open Street Map is used to display the location of the user.
 */
@Composable
fun LoadMap(latitude: Double, longitude: Double) {

    val mapShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val pathData =
                """M163.22,0.51C212.18,2.92 259.16,21.35 289.89,59.4C320.79,97.66 336.34,149.24 321.06,195.93C306.97,238.98 261.23,258.63 219.1,275.64C181.4,290.87 141.04,299.17 103.37,283.84C62.63,267.27 31.34,234.72 16.79,193.34C-0.35,144.59 -11.5,87.27 19.78,46.08C51.64,4.13 110.49,-2.09 163.22,0.51Z"""
            val scaleX = size.width / 327F
            val scaleY = size.height / 292F

            return Outline.Generic(
                PathParser.createPathFromPathData(
                    resize(
                        pathData,
                        scaleX,
                        scaleY
                    )
                ).asComposePath()
            )
        }
    }

    AndroidView(
        modifier = Modifier
            .clip(shape = mapShape)
            .width(327.dp)
            .height(292.dp)
            .background(color = Color.White),
        factory = { context ->
            Configuration.getInstance().load(context, getDefaultSharedPreferences(context))
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                controller.setZoom(18.0)
                controller.animateTo(GeoPoint(latitude, longitude))
                controller.setCenter(GeoPoint(latitude, longitude))

                val marker = Marker(this)
                marker.position = GeoPoint(latitude, longitude)
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                marker.setIcon(ContextCompat.getDrawable(context, R.drawable.ic_map_ellipse))
                marker.title = "My Location"
                marker.snippet = "Latitude: $latitude, Longitude: $longitude"
                overlays.add(marker)

                val targetMarker = Marker(this)
                targetMarker.position = GeoPoint(latitude, longitude)
                targetMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
                targetMarker.setIcon(ContextCompat.getDrawable(context, R.drawable.location))
                targetMarker.title = "Target Location"
                targetMarker.snippet = "Latitude: $latitude, Longitude: $longitude"
                overlays.add(targetMarker)

                invalidate()
            }
        })

}

private fun resize(pathData: String, scaleX: Float, scaleY: Float): String {
    val matcher =
        Pattern.compile("[0-9]+[.]?([0-9]+)?").matcher(pathData) // match the numbers in the path
    val stringBuffer = StringBuffer()
    var count = 0
    while (matcher.find()) {
        val number = matcher.group().toFloat()
        matcher.appendReplacement(
            stringBuffer,
            (if (count % 2 == 0) number * scaleX else number * scaleY).toString()
        ) // replace numbers with scaled numbers
        ++count
    }
    return stringBuffer.toString() // return the scaled path
}

