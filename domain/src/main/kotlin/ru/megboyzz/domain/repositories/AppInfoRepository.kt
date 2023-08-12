package ru.megboyzz.domain.repositories

import ru.megboyzz.domain.eitities.AppInfo

interface AppInfoRepository {

    fun getInfoAboutApp(pathToApk: String): AppInfo?

}