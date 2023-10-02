package ru.megboyzz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.megboyzz.AsyncImage
import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.theme.*

@Composable
fun VersionShield(versionName: String?) {

    Card(
        elevation = 4.dp,
        // TODO возможно стоит увеличить
        modifier = Modifier.size(60.dp, 20.dp),
        backgroundColor = if(versionName != null) accent else error,
        shape = RoundedCornerShape(5.dp)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = versionName ?: "Нет версии",
                style = H2,
                color = white
            )
        }
    }

}

@Composable
fun ApplicationCard(appInfo: AppInfo) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.size(260.dp, 48.dp)
    ){
        val density = LocalDensity.current
        AsyncImage(
            load = {
                   runCatching {
                       loadImageBitmap(appInfo.imageContent)
                   }.getOrDefault(
                       useResource("common/images/help.svg"){
                           loadSvgPainter(it, density)
                       }
                   )
            },
            painterFor = { if(it is ImageBitmap) BitmapPainter(it) else it as Painter  },
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.size(48.dp)
        )

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.height(48.dp)
        ) {
            Row(
                modifier = Modifier.height(18.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = appInfo.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = H1,
                    modifier = Modifier.width(110.dp)
                )
                if(appInfo.version  != null)
                    VersionShield(appInfo.version!!)
            }
            Text(
               text = appInfo.packageName,
               style = H2,
            )
        }

    }

}

@Composable
fun InfoShield(
    infoMsg: String,
    isError: Boolean = false,
    inProcess: Boolean
) {
    if (inProcess)
        CircularProgressIndicator(
            strokeWidth = 1.dp,
            modifier = Modifier.size(10.dp)
        )
    else {
        Text(
            text = infoMsg,
            style = H3,
            color = if (isError) error else success
        )
    }
}
