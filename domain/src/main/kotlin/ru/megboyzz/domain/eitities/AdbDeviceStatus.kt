package ru.megboyzz.domain.eitities

import java.util.*

enum class AdbDeviceStatus {
    OFFLINE,
    DEVICE,
    RECOVERY,
    SIDELOAD,
    UNAUTHORIZED,
    NO_PERMISSIONS,
    HOST,
    UNKNOWN_STATUS;

    override fun toString(): String {
        return super.toString()
            .lowercase(Locale.getDefault())
            .replace("_", " ")
    }

    companion object{

        fun strAsAdbDeviceStatus(string: String): AdbDeviceStatus{

            val str = string.uppercase().replace(" ", "_")

            val values = AdbDeviceStatus.values()
            values.forEach {
                if(str == it.name) return it
            }
            return UNKNOWN_STATUS
        }

    }

}