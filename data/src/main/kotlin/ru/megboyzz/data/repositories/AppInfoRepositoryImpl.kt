package ru.megboyzz.data.repositories

import net.dongliu.apk.parser.ApkFile
import ru.megboyzz.domain.eitities.AppInfo
import ru.megboyzz.domain.repositories.AppInfoRepository
import java.io.File
import java.io.InputStream

class AppInfoRepositoryImpl : AppInfoRepository {
    override fun getInfoAboutApp(pathToApk: String): AppInfo? {

        val apk = File(pathToApk)

        if(!apk.exists()) return null

        val apkFile = ApkFile(apk)

        val imageContent = if(!apkFile.apkMeta.icon.contains(".xml"))
            apkFile.iconFile.data!!.inputStream()
        else
            InputStream.nullInputStream() // TODO Обработать случай с XML/SVG


        return AppInfo(
            name = apkFile.apkMeta.name,
            packageName = apkFile.apkMeta.packageName,
            version = apkFile.apkMeta.versionName,
            imagePath = apkFile.apkMeta.icon,
            pathToApk = pathToApk,
            imageContent = imageContent
        )

    }
}