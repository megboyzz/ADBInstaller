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

    val isConnecting = MutableStateFlow(false)

    val isErrorConnecting = MutableStateFlow(false)

    val connectingMessage = MutableStateFlow("")

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

    fun connectTo(adbDeviceName: String) = coroutineScope.launch(Dispatchers.IO) {

        //Сброс
        isErrorConnecting.emit(false)
        connectingMessage.emit("")

        isConnecting.emit(true)
        val adbDevice = useCaseComponent.connectToNewAdbDeviceUseCase(adbDeviceName)
        if(adbDevice == null) {
            isErrorConnecting.emit(true)
            connectingMessage.emit("Ошибка подключения к $adbDeviceName")
        }else
            connectingMessage.emit("Успешно подключено к $adbDeviceName")

        isConnecting.emit(false)
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

        isError.emit(false)

        isInstalling.emit(true)

        if (!useCaseComponent.updateAdbDevicesUseCase(adbDevice)) {
            installStatus.emit("Ошибка проверки ADB устройства")
            isInstalling.emit(false)
            return@launch
        }

        updateDevices()

        if(appInfo.value != null) {

            val isInstallSuccess = useCaseComponent.installAppUseCase(appInfo.value!!, adbDevice)

            if(isInstallSuccess)
                installStatus.emit("Успешно установлено на ${adbDevice.name}")
            else {
                isError.emit(true)
                installStatus.emit("Ошибка установки")
            }
        }else
            installStatus.emit("Ошибка установки")


        isInstalling.emit(false)

    }


}