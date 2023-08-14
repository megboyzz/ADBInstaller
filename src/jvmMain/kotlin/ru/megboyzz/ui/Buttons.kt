package ru.megboyzz.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import jdk.jfr.Enabled
import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.domain.eitities.AdbDeviceStatus
import ru.megboyzz.rememberSvg
import ru.megboyzz.theme.H1
import ru.megboyzz.theme.accent
import ru.megboyzz.theme.subAccent
import ru.megboyzz.theme.white

@Composable
fun InstallAppButton(
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = accent
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.size(260.dp, 35.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp,
            hoveredElevation = 16.dp,
            focusedElevation = 16.dp
        )

    ) {
        Text(
            text = "Установить",
            color = white,
            style = H1
        )
    }

}

@Composable
fun DropdownMenu(
    visibleItemIndex: Int = 0,
    items: List<AdbDevice>,
    onSelect: (adbDeviceIndex: Int) -> Unit
) {

    val arrow = rememberSvg("common/images/arrow.svg")
    val arrowExpanded = rememberSvg("common/images/arrow_expanded.svg")

    var expanded by remember { mutableStateOf(false) }

    val defaultCorner = 10.dp
    val noDevices = items.isEmpty()

    val shape = RoundedCornerShape(
        topStart = defaultCorner,
        topEnd = defaultCorner,
        bottomStart = if (expanded) 0.dp else defaultCorner,
        bottomEnd = if (expanded) 0.dp else defaultCorner
    )

    Box(
        modifier = Modifier.size(260.dp, 130.dp)
    ){
        Column {
            Card(
                elevation = 4.dp,
                backgroundColor = accent,
                shape = shape,
                modifier = Modifier
                    .size(260.dp, 35.dp)
                    .clip(shape)
                    .clickable(enabled = !noDevices) { expanded = !expanded }
            ) {

                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = if (noDevices) "Нет устройств" else items[visibleItemIndex].name,
                        style = H1,
                        color = white
                    )

                    Icon(
                        painter = if (expanded) arrowExpanded else arrow,
                        tint = if(noDevices) Color.Gray else white,
                        contentDescription = null
                    )

                }

            }
            if (expanded) {
                Card(
                    elevation = 4.dp,
                    backgroundColor = subAccent,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = defaultCorner,
                        bottomStart = defaultCorner
                    )
                ) {
                    Column(
                        Modifier.scrollable(
                            state = rememberScrollState(),
                            orientation = Orientation.Horizontal
                            )
                    ) {
                        items.forEachIndexed { index, adbDevice ->
                            if(adbDevice.status == AdbDeviceStatus.DEVICE)
                            DropdownMenuItem(
                                modifier = Modifier.height(35.dp),
                                onClick = {
                                    onSelect(index)
                                    expanded = !expanded
                                }
                            ) {
                                Text(
                                    text = "${index + 1}. ${adbDevice.name}",
                                    style = H1,
                                    color = white
                                )
                            }
                        }
                    }
                }
            }
        }

    }

}



