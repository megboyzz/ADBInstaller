package ru.megboyzz.data.repositories

import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.domain.repositories.AppInfoRepository

class AppInfoRepositoryImpl : AppInfoRepository {
    override fun getInfoAboutApp(pathToApk: String): AppInfo {
        TODO("Not yet implemented")
    }
}