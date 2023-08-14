import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import ru.megboyzz.ui.MainScreen
import ru.megboyzz.ui.MainWindow


fun main(arg: Array<String>) = application {

    val arg1 = "C:\\Users\\ikits\\Downloads\\com.android.systemui_10-29_minAPI29(nodpi)_apkmirror.com.apk"

    MainWindow(
        ::exitApplication
    ){
        Navigator(MainScreen(arg1))
    }
}
