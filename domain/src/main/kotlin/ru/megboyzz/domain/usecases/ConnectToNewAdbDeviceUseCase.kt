package ru.megboyzz.domain.usecases

import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.domain.repositories.ADBRepository

class ConnectToNewAdbDeviceUseCase(
    private val adbRepository: ADBRepository,
    private val updateAdbDevicesUseCase: UpdateAdbDevicesUseCase
) {

    operator fun invoke(nameOrIp: String): AdbDevice?{

        val newAdbDevice = adbRepository.connectToNewAdbDevice(nameOrIp)

        return if(newAdbDevice != null && updateAdbDevicesUseCase(newAdbDevice))
                newAdbDevice
            else
                null

    }

}