package ru.megboyzz.domain.usecases

import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.domain.repositories.AppInfoRepository
import java.io.File

class OpenAppUseCase(private val appInfoRepository: AppInfoRepository) {

    operator fun invoke(pathToApk: String): AppInfo?{
        val file = File(pathToApk)
        return if(file.exists())
            appInfoRepository.getInfoAboutApp(pathToApk)
        else null
    }

}