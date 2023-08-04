package ru.megboyzz.domain.repositories

import ru.megboyzz.domain.eitities.ADBDevice
import ru.megboyzz.domain.eitities.AppInfo

interface ADBRepository {

    //Read-метод репозитория
    fun getListOfADBDevices(): List<ADBDevice>

    //Write-метод репозитория
    fun installApplication(appInfo: AppInfo, adbDevice: ADBDevice): Boolean

}