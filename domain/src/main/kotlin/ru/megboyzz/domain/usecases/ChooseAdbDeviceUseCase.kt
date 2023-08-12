package ru.megboyzz.domain.usecases

import ru.megboyzz.domain.eitities.ADBDevice

class ChooseAdbDeviceUseCase(
    val updateADBDevicesUseCase: UpdateAdbDevicesUseCase
) {

    operator fun invoke(adbDevice: ADBDevice): ADBDevice? =
        if(updateADBDevicesUseCase(adbDevice))
            adbDevice
        else
            null

}