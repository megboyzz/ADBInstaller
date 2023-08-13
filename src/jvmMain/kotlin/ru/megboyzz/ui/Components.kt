package ru.megboyzz.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.loadXmlImageVector
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.dongliu.apk.parser.ApkFile
import org.xml.sax.InputSource
import ru.megboyzz.AsyncImage
import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.theme.*
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.io.InputStream

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

fun InputStream.isEmpty(): Boolean = this.available() == 0

@Composable
fun ApplicationCard(appInfo: AppInfo) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.size(260.dp, 48.dp)
    ){

        AsyncImage(
            load = { loadImageBitmap(appInfo.imageContent) },
            painterFor = { BitmapPainter(it) },
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
                VersionShield(appInfo.version)
            }
            Text(
               text = appInfo.packageName,
               style = H2,
            )
        }

    }

}
