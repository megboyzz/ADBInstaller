package ru.megboyzz.data.di.modules

import dagger.Module
import dagger.Provides
import ru.megboyzz.data.repositories.AdbRepositoryImpl
import ru.megboyzz.data.repositories.AppInfoRepositoryImpl
import ru.megboyzz.domain.repositories.ADBRepository
import ru.megboyzz.domain.repositories.AppInfoRepository

@Module
class RepositoryModule(
    private val pathToAapt: String,
    private val pathToAdb: String
) {

    @Provides
    fun providesAppInfoRepository(): AppInfoRepository
        = AppInfoRepositoryImpl(pathToAapt = pathToAapt)


    @Provides
    fun providesAdbRepository(): ADBRepository
        = AdbRepositoryImpl(pathToAdb)


}