package ru.megboyzz.ui

import androidx.compose.runtime.*
import cafe.adriel.voyager.core.screen.Screen
import ru.megboyzz.composeResourcesDir
import ru.megboyzz.data.di.components.DaggerUseCaseComponent
import ru.megboyzz.data.di.modules.RepositoryModule

class MainScreen : Screen {
    @Composable
    override fun Content() {

        val useCaseComponent = DaggerUseCaseComponent.builder().repositoryModule(
            repositoryModule = RepositoryModule(
                pathToAapt = "$composeResourcesDir/aapt",
                pathToAdb = "$composeResourcesDir/adb"
            )
        ).build()

        VersionShield("v1.3.72")


    }
}