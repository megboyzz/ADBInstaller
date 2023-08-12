package ru.megboyzz.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import ru.megboyzz.composeResourcesDir
import ru.megboyzz.data.di.components.DaggerUseCaseComponent
import ru.megboyzz.data.di.modules.RepositoryModule
import ru.megboyzz.ui.DropdownMenu
import java.io.File

class MainScreen : Screen {
    @Composable
    override fun Content() {

        val useCaseComponent = DaggerUseCaseComponent.builder().repositoryModule(
            repositoryModule = RepositoryModule(
                pathToAapt = "$composeResourcesDir/aapt",
                pathToAdb = "$composeResourcesDir/adb"
            )
        ).build()





    }
}