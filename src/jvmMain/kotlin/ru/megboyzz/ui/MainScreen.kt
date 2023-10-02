package ru.megboyzz.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import ru.megboyzz.rememberSvg
import ru.megboyzz.theme.*

class MainScreen(private val pathToApk: String) : Screen {

    private val tag = "MY_TAG"

    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel(tag) { MainScreenModel() }

        screenModel.openApp(pathToApp = pathToApk)

        val appInfo = screenModel.appInfo.collectAsState()

        val isApkInfoLoading = screenModel.isApkInfoLoading.collectAsState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize().padding(top = 10.dp)
        ) {
            if(appInfo.value != null) {
                ApplicationCard(appInfo.value!!)
                var isShowAdbConnect by remember { mutableStateOf(false) }
                TitleAndButton(
                    expanded = isShowAdbConnect,
                    onClick = {
                        isShowAdbConnect =! isShowAdbConnect
                    }
                )
                Crossfade(targetState = isShowAdbConnect){
                    if(it)
                        ConnectButtons()
                    else
                        InstallButtons()
                }
            }else {
                if(isApkInfoLoading.value)
                    CircularProgressIndicator()
                else {
                    Text(
                        text = "apk файл не выбран",
                        color = error,
                        style = H1,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }

        }

    }

    @Composable
    fun TitleAndButton(
        expanded: Boolean,
        onClick: () -> Unit
    ) {

        val arrow = rememberSvg("common/images/arrow_expanded.svg")
        val rotatedFloat by animateFloatAsState(
            targetValue = if (expanded) 0f else -180f
        )
        Row(Modifier.fillMaxWidth().padding(15.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable(onClick = onClick)
            ) {
                Text(
                    text = "Устройства ADB",
                    style = H1,
                )
                Icon(
                    painter = arrow,
                    tint = black,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotatedFloat)
                )
            }
        }
    }

    @Composable
    fun ConnectButtons() {

        val screenModel = rememberScreenModel(tag) { MainScreenModel() }

        val isConnecting by screenModel.isConnecting.collectAsState()

        val connectingMessage by screenModel.connectingMessage.collectAsState()

        val isConnectingError by screenModel.isErrorConnecting.collectAsState()

        var adbName by remember { mutableStateOf("") }

        Column(
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ConnectTextField(
                value = adbName,
                onValueChange = { adbName = it }
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConnectAdbDeviceButton(
                    onClick = {
                        screenModel.connectTo(adbName)
                    }
                )
                InfoShield(
                    inProcess = isConnecting,
                    isError = isConnectingError,
                    infoMsg = connectingMessage
                )
            }
        }

    }

    @Composable
    fun InstallButtons() {

        val screenModel = rememberScreenModel(tag) { MainScreenModel() }

        val chosenDeviceIndex = screenModel.chosenDeviceIndex.collectAsState()

        val isInstalling = screenModel.isInstalling.collectAsState()

        val isError = screenModel.isError.collectAsState()

        val adbDevices = screenModel.adbDevices.collectAsState()

        val status = screenModel.installStatus.collectAsState()

        Box {
            Column {
                Spacer(Modifier.height(50.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    InstallAppButton(
                        enabled = adbDevices.value.isNotEmpty()
                    ) {
                        screenModel.installApp(adbDevices.value[chosenDeviceIndex.value])
                    }
                    InfoShield(
                        inProcess = isInstalling.value,
                        infoMsg = status.value,
                        isError = isError.value
                    )
                }
            }
            DropdownMenu(
                visibleItemIndex = chosenDeviceIndex.value,
                items = adbDevices.value,
                onSelect = { screenModel.setVisibleDevice(it) }
            )
        }

    }

}
