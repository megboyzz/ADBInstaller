package ru.megboyzz.data.di.modules

import dagger.Module
import dagger.Provides
import ru.megboyzz.domain.repositories.ADBRepository
import ru.megboyzz.domain.repositories.AppInfoRepository
import ru.megboyzz.domain.usecases.*

// TODO Возможно стоит добавить Inject на конструктор а usecase, но для этого нужна зависимость javax
@Module
class UseCaseModule {

    @Provides
    fun providesUpdateAdbDevicesUseCase(
        adbRepository: ADBRepository
    ): UpdateAdbDevicesUseCase
        = UpdateAdbDevicesUseCase(adbRepository)

    @Provides
    fun providesGetListOfAdbDevicesUseCase(
        adbRepository: ADBRepository
    ): GetListOfAdbDevicesUseCase
        = GetListOfAdbDevicesUseCase(adbRepository)

    @Provides
    fun providesOpenAppUseCase(
        appInfoRepository: AppInfoRepository
    ): OpenAppUseCase
        = OpenAppUseCase(appInfoRepository)



    @Provides
    fun providesChooseAdbDeviceUseCase(
        updateAdbDevicesUseCase: UpdateAdbDevicesUseCase
    ): ChooseAdbDeviceUseCase
    = ChooseAdbDeviceUseCase(updateAdbDevicesUseCase)


    @Provides
    fun providesInstallAppUseCase(
        verifyADBDevicesUseCase: UpdateAdbDevicesUseCase,
        adbRepository: ADBRepository
    ): InstallAppUseCase
        = InstallAppUseCase(verifyADBDevicesUseCase, adbRepository)

}