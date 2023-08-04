package ru.megboyzz.domain.eitities

import java.util.*

enum class ADBDeviceStatus {
    OFFLINE,
    DEVICE,
    RECOVERY,
    SIDELOAD,
    UNAUTHORIZED,
    NO_PERMISSIONS,
    HOST;

    override fun toString(): String {
        return super.toString()
            .lowercase(Locale.getDefault())
            .replace("_", " ")
    }
}