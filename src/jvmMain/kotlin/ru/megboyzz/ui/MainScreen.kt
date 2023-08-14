package ru.megboyzz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import ru.megboyzz.theme.H1
import ru.megboyzz.theme.H3
import ru.megboyzz.theme.success

class MainScreen(private val pathToApk: String) : Screen {

    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel { MainScreenModel() }

        screenModel.openApp(pathToApp = pathToApk)

        val appInfo = screenModel.appInfo.collectAsState()

        val chosenDeviceIndex = screenModel.chosenDeviceIndex.collectAsState()

        val isInstalling = screenModel.isInstalling.collectAsState()

        val isError = screenModel.isError.collectAsState()

        val adbDevices = screenModel.adbDevices.collectAsState()

        val status = screenModel.installStatus.collectAsState()

        val isApkInfoLoading = screenModel.isApkInfoLoading.collectAsState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize().padding(top = 10.dp)
        ) {
            if(appInfo.value != null) {
                ApplicationCard(appInfo.value!!)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(15.dp)
                ) {
                    Text(
                        text = "Устройства ADB",
                        style = H1,
                    )
                }
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
                            if (isInstalling.value)
                                CircularProgressIndicator(
                                    strokeWidth = 1.dp,
                                    modifier = Modifier.size(10.dp)
                                )
                            else {
                                Text(
                                    text = status.value,
                                    style = H3,
                                    color = if (isError.value) ru.megboyzz.theme.error else success
                                )
                            }
                        }
                    }
                    DropdownMenu(
                        visibleItemIndex = chosenDeviceIndex.value,
                        items = adbDevices.value,
                        onSelect = { screenModel.setVisibleDevice(it) }
                    )
                }
            }else {
                if(isApkInfoLoading.value)
                    CircularProgressIndicator()
                else {
                    Text(
                        text = "apk файл не выбран",
                        color = ru.megboyzz.theme.error,
                        style = H1,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }

        }

    }
}