package ru.megboyzz.data.repositories

import ru.megboyzz.data.core.cmd.exec
import ru.megboyzz.domain.eitities.ADBDevice
import ru.megboyzz.domain.eitities.ADBDeviceStatus
import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.domain.repositories.ADBRepository

class AdbRepositoryImpl(private val pathToAdb:String) : ADBRepository {

    private val listStart = "List of devices attached"
    override fun getListOfADBDevices(): List<ADBDevice> {

        val list = mutableListOf<ADBDevice>()

        val out = exec(pathToAdb, "devices")
            .split(listStart)[1]
                .split("\n")

        for(adbStr in out){
            if(adbStr.length > 1) {
                val device = adbStr.replace("\r", "").split("\t")
                val adbDevice = ADBDevice(
                    name = device[0],
                    status = ADBDeviceStatus.strAsAdbDeviceStatus(device[1])
                )
                list += adbDevice
            }
        }

        return list

    }

    override fun installApplication(appInfo: AppInfo, adbDevice: ADBDevice): Boolean {

        val listOfADBDevices = getListOfADBDevices()
        val adbDeviceCheck = listOfADBDevices.find { it.name == adbDevice.name }

        if(adbDeviceCheck != null && adbDeviceCheck.status == ADBDeviceStatus.DEVICE){

            val exec = exec(pathToAdb, "-s", adbDevice.name, "install", appInfo.pathToApk)
            return exec.contains("Success")

        }else return false

    }
}