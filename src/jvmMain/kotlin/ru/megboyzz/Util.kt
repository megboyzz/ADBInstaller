package ru.megboyzz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource

val composeResourcesDir: String = System.getProperty("compose.application.resources.dir")

@Composable
fun rememberSvg(svgResource: String): Painter {
    val density = LocalDensity.current
    return remember { useResource(svgResource){
        loadSvgPainter(it, density)
    } }
}