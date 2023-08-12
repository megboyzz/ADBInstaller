package ru.megboyzz.data.di.components

import dagger.Component
import ru.megboyzz.data.di.modules.RepositoryModule
import ru.megboyzz.data.di.modules.UseCaseModule
import ru.megboyzz.domain.usecases.*
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, UseCaseModule::class])
interface UseCaseComponent {

    val chooseAdbDeviceUseCase: ChooseAdbDeviceUseCase

    val getListOfAdbDevicesUseCase: GetListOfAdbDevicesUseCase

    val installAppUseCase: InstallAppUseCase

    val openAppUseCase: OpenAppUseCase

    val updateAdbDevicesUseCase: UpdateAdbDevicesUseCase

    @Component.Builder
    interface Builder {
        fun repositoryModule(
            repositoryModule: RepositoryModule
        ): Builder // add this
        fun build(): UseCaseComponent
    }

}