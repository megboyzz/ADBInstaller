package ru.megboyzz.domain.usecases

import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.domain.repositories.ADBRepository

class GetListOfAdbDevicesUseCase(
    private val adbRepository: ADBRepository
) {

    operator fun invoke(): List<AdbDevice> = adbRepository.getListOfADBDevices()

}