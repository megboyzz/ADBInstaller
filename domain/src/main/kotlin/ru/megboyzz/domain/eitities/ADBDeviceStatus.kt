package ru.megboyzz.domain.eitities

import java.util.*

enum class ADBDeviceStatus {
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

        fun strAsAdbDeviceStatus(string: String): ADBDeviceStatus{

            val str = string.uppercase().replace(" ", "_")

            val values = ADBDeviceStatus.values()
            values.forEach {
                if(str == it.name) return it
            }
            return UNKNOWN_STATUS
        }

    }

}