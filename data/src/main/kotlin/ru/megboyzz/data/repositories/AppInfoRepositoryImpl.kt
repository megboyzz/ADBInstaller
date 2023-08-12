package ru.megboyzz.data.repositories

import ru.megboyzz.data.core.parsers.AaptParser
import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.domain.repositories.AppInfoRepository
import java.io.File

class AppInfoRepositoryImpl(private val pathToAapt: String) : AppInfoRepository {
    override fun getInfoAboutApp(pathToApk: String): AppInfo? {

        val apk = File(pathToApk)

        if(!apk.exists()) return null

        val parse: AaptParser = AaptParser.parse(pathToAapt, apk)

        return AppInfo(
            name = parse.label,
            packageName = parse.packageName,
            version = parse.versionName,
            image = parse.imagePath,
            pathToApk = pathToApk
        )

    }
}