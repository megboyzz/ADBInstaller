package ru.megboyzz.domain.usecases

import ru.megboyzz.domain.eitities.AdbDevice

class ChooseAdbDeviceUseCase(
    val updateADBDevicesUseCase: UpdateAdbDevicesUseCase
) {

    operator fun invoke(adbDevice: AdbDevice): AdbDevice? =
        if(updateADBDevicesUseCase(adbDevice))
            adbDevice
        else
            null

}