import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cafe.adriel.voyager.navigator.Navigator
import com.sun.tools.javac.Main
import ru.megboyzz.ui.MainScreen
import ru.megboyzz.ui.MainWindow


fun main() = application {

    MainWindow(
        ::exitApplication
    ){
        Navigator(MainScreen())
    }
}
