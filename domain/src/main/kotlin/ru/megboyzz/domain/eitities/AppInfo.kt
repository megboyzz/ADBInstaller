package ru.megboyzz.domain.eitities

data class AppInfo(
    val name: String,
    val packageName: String,
    //Это путь внутри APK
    val image: String,
    val version: String,
    val pathToApk: String
)
