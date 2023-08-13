package ru.megboyzz.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.megboyzz.theme.H2
import ru.megboyzz.theme.H3
import ru.megboyzz.theme.accent
import ru.megboyzz.theme.white
@Composable
fun VersionShield(versionName: String) {

    Card(
        elevation = 4.dp,
        // TODO возможно стоит увеличить
        modifier = Modifier.size(60.dp, 20.dp),
        backgroundColor = accent,
        shape = RoundedCornerShape(5.dp)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = versionName,
                style = H2,
                color = white
            )
        }
    }

}

