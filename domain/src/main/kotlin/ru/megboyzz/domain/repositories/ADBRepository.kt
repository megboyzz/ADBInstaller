package ru.megboyzz.domain.repositories

import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.domain.eitities.AppInfo

interface ADBRepository {

    //Read-метод репозитория
    fun getListOfADBDevices(): List<AdbDevice>

    //Write-метод репозитория
    fun installApplication(appInfo: AppInfo, adbDevice: AdbDevice): Boolean

}