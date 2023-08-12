package ru.megboyzz.data.repositories

import ru.megboyzz.data.core.cmd.exec
import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.domain.eitities.AdbDeviceStatus
import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.domain.repositories.ADBRepository

class AdbRepositoryImpl(private val pathToAdb:String) : ADBRepository {

    private val listStart = "List of devices attached"
    override fun getListOfADBDevices(): List<AdbDevice> {

        val list = mutableListOf<AdbDevice>()

        val out = exec(pathToAdb, "devices")
            .split(listStart)[1]
                .split("\n")

        for(adbStr in out){
            if(adbStr.length > 1) {
                val device = adbStr.replace("\r", "").split("\t")
                val adbDevice = AdbDevice(
                    name = device[0],
                    status = AdbDeviceStatus.strAsAdbDeviceStatus(device[1])
                )
                list += adbDevice
            }
        }

        return list

    }

    override fun installApplication(appInfo: AppInfo, adbDevice: AdbDevice): Boolean {

        val listOfADBDevices = getListOfADBDevices()
        val adbDeviceCheck = listOfADBDevices.find { it.name == adbDevice.name }

        if(adbDeviceCheck != null && adbDeviceCheck.status == AdbDeviceStatus.DEVICE){

            val exec = exec(pathToAdb, "-s", adbDevice.name, "install", appInfo.pathToApk)
            return exec.contains("Success")

        }else return false

    }
}