package ru.megboyzz.domain.usecases

import ru.megboyzz.domain.eitities.ADBDevice

class ChooseADBDevice(val updateADBDevicesUseCase: UpdateADBDevicesUseCase) {

    operator fun invoke(adbDevice: ADBDevice): ADBDevice? =
        if(updateADBDevicesUseCase(adbDevice))
            adbDevice
        else
            null

}