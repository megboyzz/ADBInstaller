package ru.megboyzz.data.repositories

import ru.megboyzz.domain.eitities.ADBDevice
import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.domain.repositories.ADBRepository

class ADBRepositoryImpl : ADBRepository {
    override fun getListOfADBDevices(): List<ADBDevice> {
        TODO("Not yet implemented")
    }

    override fun installApplication(appInfo: AppInfo, adbDevice: ADBDevice): Boolean {
        TODO("Not yet implemented")
    }
}