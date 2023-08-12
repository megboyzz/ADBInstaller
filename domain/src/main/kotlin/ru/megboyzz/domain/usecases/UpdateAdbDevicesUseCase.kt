package ru.megboyzz.domain.usecases

import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.domain.repositories.ADBRepository

class UpdateAdbDevicesUseCase(private val adbRepository: ADBRepository) {

    operator fun invoke(adbDevice: AdbDevice): Boolean {

        val listOfADBDevices = adbRepository.getListOfADBDevices()

        return adbDevice in listOfADBDevices

    }


}