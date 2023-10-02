package ru.megboyzz.domain.eitities

import java.io.InputStream

data class AppInfo(
    val name: String,
    val packageName: String,
    //Это путь внутри APK
    val imagePath: String,
    val imageContent: InputStream,
    val version: String?,
    val pathToApk: String
)
