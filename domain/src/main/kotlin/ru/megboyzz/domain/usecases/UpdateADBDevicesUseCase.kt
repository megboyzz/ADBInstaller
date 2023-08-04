package ru.megboyzz.domain.usecases

import ru.megboyzz.domain.eitities.ADBDevice
import ru.megboyzz.domain.repositories.ADBRepository

class UpdateADBDevicesUseCase(private val adbRepository: ADBRepository) {

    operator fun invoke(adbDevice: ADBDevice): Boolean {

        val listOfADBDevices = adbRepository.getListOfADBDevices()

        return adbDevice in listOfADBDevices

    }


}