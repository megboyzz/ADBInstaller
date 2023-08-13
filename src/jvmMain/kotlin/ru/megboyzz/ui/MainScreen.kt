package ru.megboyzz.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import ru.megboyzz.composeResourcesDir
import ru.megboyzz.data.di.components.DaggerUseCaseComponent
import ru.megboyzz.data.di.modules.RepositoryModule
import ru.megboyzz.domain.usecases.InstallAppUseCase
import ru.megboyzz.theme.H1
import ru.megboyzz.theme.H3

class MainScreen : Screen {

    private val useCaseComponent = DaggerUseCaseComponent.builder().repositoryModule(
        repositoryModule = RepositoryModule(
            pathToAdb = "$composeResourcesDir/adb"
        )
    ).build()

    @Composable
    override fun Content() {

        val openAppUseCase = useCaseComponent.openAppUseCase

        val appInfo = openAppUseCase("C:\\Users\\ikits\\Downloads\\NFS_MW_Max_Graphics_v1.3.71[na].apk")

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize().padding(top = 10.dp)
        ) {
            if(appInfo != null)
                ApplicationCard(appInfo)
            else
                Text(
                    text = "apk файл не выбран",
                    color = ru.megboyzz.theme.error,
                    style = H1,
                    modifier = Modifier.size(100.dp)
                )
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(15.dp)
            ){
                Text(
                    text = "Устройства ADB",
                    style = H1,
                )
            }
            Box{
                Column{
                    Spacer(Modifier.height(50.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        InstallButton { }
                        CircularProgressIndicator(strokeWidth = 1.dp, modifier = Modifier.size(10.dp))
                    }
                }
                DropdownMenu(
                    items = useCaseComponent.getListOfAdbDevicesUseCase(),
                    onSelect = {

                    }
                )
            }

        }

    }
}