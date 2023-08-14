package ru.megboyzz.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.megboyzz.composeResourcesDir
import ru.megboyzz.data.di.components.DaggerUseCaseComponent
import ru.megboyzz.data.di.modules.RepositoryModule
import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.domain.eitities.AppInfo

class MainScreenModel: ScreenModel {

    private val adbPollingFrequency = 1000L

    private val useCaseComponent = DaggerUseCaseComponent.builder().repositoryModule(
        repositoryModule = RepositoryModule(
            pathToAdb = "$composeResourcesDir/adb"
        )
    ).build()

    val appInfo: MutableStateFlow<AppInfo?> = MutableStateFlow(null)

    val adbDevices = MutableStateFlow<List<AdbDevice>>(listOf())

    val chosenDeviceIndex = MutableStateFlow(0)

    val installStatus = MutableStateFlow("")

    val isInstalling = MutableStateFlow(false)

    val isError = MutableStateFlow(false)

    val isApkInfoLoading = MutableStateFlow(false)

    init {
        startMonitoringADB()
    }

    private fun startMonitoringADB() = coroutineScope.launch(Dispatchers.IO) {

        while(true){
            if(appInfo.value != null) {
                adbDevices.emit(useCaseComponent.getListOfAdbDevicesUseCase())

                delay(adbPollingFrequency)
            }
        }

    }

    fun openApp(pathToApp: String) = coroutineScope.launch(Dispatchers.IO) {
            isApkInfoLoading.emit(true)
            appInfo.emit(useCaseComponent.openAppUseCase(pathToApp))
            isApkInfoLoading.emit(false)
    }

    fun setVisibleDevice(index: Int) = coroutineScope.launch(Dispatchers.IO) {
        if(adbDevices.value.size > index) chosenDeviceIndex.emit(index)
    }

    fun updateDevices() = coroutineScope.launch(Dispatchers.IO) {
        adbDevices.emit(useCaseComponent.getListOfAdbDevicesUseCase())
    }

    fun installApp(adbDevice: AdbDevice) = coroutineScope.launch(Dispatchers.IO) {

        isInstalling.emit(true)

        if (!useCaseComponent.updateAdbDevicesUseCase(adbDevice)) {
            installStatus.emit("Ошибка проверки ADB устройства")
            isInstalling.emit(false)
            return@launch
        }

        updateDevices()

        if(appInfo.value != null)
            useCaseComponent.installAppUseCase(appInfo.value!!, adbDevice)
        else
            installStatus.emit("Ошибка установки")

        installStatus.emit("Успешно установлено на ${adbDevice.name}")

        isInstalling.emit(false)

    }


}