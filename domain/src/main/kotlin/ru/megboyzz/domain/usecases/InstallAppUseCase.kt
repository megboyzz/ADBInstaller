package ru.megboyzz.domain.usecases

import ru.megboyzz.domain.eitities.AdbDevice
import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.domain.repositories.ADBRepository

class InstallAppUseCase(
    private val verifyADBDevicesUseCase: UpdateAdbDevicesUseCase,
    private val adbRepository: ADBRepository
) {

    operator fun invoke(appInfo: AppInfo, adbDevice: AdbDevice): Boolean{

        return if(verifyADBDevicesUseCase(adbDevice))
            adbRepository.installApplication(appInfo, adbDevice)
        else false

    }

}