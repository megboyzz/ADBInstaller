package ru.megboyzz.data

import net.dongliu.apk.parser.ApkFile

fun main(){

    val apkFile = ApkFile("D:\\Projects\\AndroidStudioProjects\\MyDnevnik\\app\\release\\app-release.apk")

    val metaInfo = apkFile.apkMeta

    println(apkFile.transBinaryXml(metaInfo.icon))

    print(metaInfo)


}