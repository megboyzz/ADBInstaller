package ru.megboyzz.ui

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.megboyzz.domain.eitities.AdbDevice

class MainScreenModel: ScreenModel {

    val appInfo = MutableStateFlow(null)

    val adbDevices = MutableStateFlow<List<AdbDevice>>(listOf())

    init {

        coroutineScope.launch(Dispatchers.IO) {

        }

    }

}