package ru.megboyzz.data

import net.dongliu.apk.parser.ApkFile


fun main(arg: Array<String>){

    val apkFile = ApkFile("C:\\Users\\ikits\\Downloads\\com.android.systemui_10-29_minAPI29(nodpi)_apkmirror.com.apk")

    println(apkFile.apkMeta)


}