package ru.megboyzz.data.di.modules

import dagger.Module
import dagger.Provides
import ru.megboyzz.data.repositories.AdbRepositoryImpl
import ru.megboyzz.data.repositories.AppInfoRepositoryImpl
import ru.megboyzz.domain.repositories.ADBRepository
import ru.megboyzz.domain.repositories.AppInfoRepository

@Module
class RepositoryModule(private val pathToAdb: String) {

    @Provides
    fun providesAppInfoRepository(): AppInfoRepository
        = AppInfoRepositoryImpl()


    @Provides
    fun providesAdbRepository(): ADBRepository
        = AdbRepositoryImpl(pathToAdb)


}