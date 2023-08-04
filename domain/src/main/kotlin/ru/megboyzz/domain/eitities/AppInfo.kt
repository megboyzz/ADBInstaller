package ru.megboyzz.domain.eitities

import javax.imageio.stream.ImageInputStream

data class AppInfo(
    val name: String,
    val packageName: String,
    val image: ImageInputStream,
    val version: String,
    val pathToApk: String
)
