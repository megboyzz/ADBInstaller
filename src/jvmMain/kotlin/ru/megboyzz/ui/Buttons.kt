package ru.megboyzz.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.domain.eitities.AdbDeviceStatus
import ru.megboyzz.rememberSvg
import ru.megboyzz.theme.*

@Composable
fun InstallAppButton(
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    BaseAppButton(
        title = "Установить",
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun ConnectAdbDeviceButton(
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    BaseAppButton(
        title = "Подключиться",
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun ConnectTextField(
    value: String,
    onValueChange: (String) -> Unit,
) {
    BaseTextField(
        hint = "Имя устройства ADB",
        value = value,
        onValueChange = onValueChange
    )
}

@Composable
fun BaseTextField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
) {

    OutlinedTextField(
        value = value,
        label = { Text(text = hint, style = H1) },
        onValueChange = onValueChange,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.size(260.dp, 55.dp).padding(0.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = accent,
            unfocusedBorderColor = accent
        )
    )
}

@Composable
fun BaseAppButton(
    title: String,
    enabled: Boolean,
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
            text = title,
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

    var expanded by remember { mutableStateOf(false) }

    val noDevices = items.isEmpty()

    ContentBox {
        HeaderDropDown(
            expanded = expanded,
            headerItem = if(noDevices) null else items[visibleItemIndex].name,
            onHeaderClick = { expanded =! expanded }
        )

        AnimatedDropdownMenuContent(
            expanded = expanded,
            items = items,
            onSelect = {
                onSelect(it)
                expanded =! expanded
            }
        )
    }

}

@Composable
fun ContentBox(
    content: @Composable () -> Unit
) {
    Box(Modifier.size(260.dp, 130.dp)) {
        Column { content() }
    }
}


@Composable
fun HeaderDropDown(
    headerItem: String?,
    expanded: Boolean,
    onHeaderClick: () -> Unit
) {

    val noDevices = headerItem == null

    val arrow = rememberSvg("common/images/arrow_expanded.svg")

    val rotatedFloat by animateFloatAsState(
        targetValue = if (expanded) 0f else -180f
    )

    val defaultCorner = 10.dp

    val animationSpecShort = tween<Dp>(
        durationMillis = 100,
    )
    val animationSpecLong = tween<Dp>(
        durationMillis = 800
    )

    val animatedCornersDp by animateDpAsState(
        targetValue = if(expanded) 0.dp else 10.dp,
        animationSpec = if(expanded) animationSpecShort else animationSpecLong
    )

    val shape = RoundedCornerShape(
        topStart = defaultCorner,
        topEnd = defaultCorner,
        bottomStart = animatedCornersDp,
        bottomEnd = animatedCornersDp
    )

    Card(
        elevation = 4.dp,
        backgroundColor = accent,
        shape = shape,
        modifier = Modifier
            .size(260.dp, 35.dp)
            .clip(shape)
            .clickable(
                enabled = !noDevices,
                onClick = onHeaderClick
            )
    ) {

        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = headerItem ?: "Нет устройств",
                style = H1,
                color = white
            )

            Icon(
                painter = arrow,
                tint = if(noDevices) Color.Gray else white,
                contentDescription = null,
                modifier = Modifier.rotate(rotatedFloat)
            )

        }

    }

}


@Composable
fun DropdownMenuContent(
    items: List<AdbDevice>,
    onSelect: (adbDeviceIndex: Int) -> Unit
) {

    val defaultCorner = 10.dp

    val shape = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomEnd = defaultCorner,
        bottomStart = defaultCorner
    )

    Card(
        elevation = 4.dp,
        backgroundColor = subAccent,
        shape = shape
    ) {
        Column(
            Modifier.scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Horizontal
            )
        ) {
            items.forEachIndexed { index, adbDevice ->
                if (adbDevice.status == AdbDeviceStatus.DEVICE)
                    DropdownMenuItem(
                        modifier = Modifier.height(35.dp),
                        onClick = {
                            onSelect(index)
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

@Composable
fun AnimatedDropdownMenuContent(
    expanded: Boolean,
    items: List<AdbDevice>,
    onSelect: (adbDeviceIndex: Int) -> Unit
) {
    AnimatedVisibility(
        visible = expanded,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        DropdownMenuContent(
            items = items,
            onSelect = { onSelect(it) }
        )
    }
}


