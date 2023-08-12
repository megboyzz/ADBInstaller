package ru.megboyzz.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState

@Composable
fun MainWindow(
    exitApplication: () -> Unit,
    content: @Composable FrameWindowScope.() -> Unit
) {

    Window(
        onCloseRequest = exitApplication,
        resizable = false,
        state = rememberWindowState(
            size = DpSize(300.dp, 260.dp)
        ),
        content = content
    )
}