package ru.megboyzz.ui

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.rememberSvg
import ru.megboyzz.theme.H1
import ru.megboyzz.theme.accent
import ru.megboyzz.theme.white

@Composable
fun InstallButton(
    onClick: () -> Unit
) {
    Button(
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
    visibleItem: AdbDevice,
    items: List<AdbDevice>,
    onSelect: (AdbDevice) -> Unit
) {

    val arrow = rememberSvg("common/images/arrow.svg")
    val arrowExpanded = rememberSvg("common/images/arrow_expanded.svg")

    var expanded by remember { mutableStateOf(false) }

    Card(
        elevation = 4.dp,
        backgroundColor = accent,
        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
        modifier = Modifier.size(260.dp, if(expanded) 70.dp else 35.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = visibleItem.name,
                style = H1,
                color = white
            )

            Icon(
                painter = if(expanded) arrowExpanded else arrow,
                tint = white,
                contentDescription = null
            )

        }

    }

}