import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import ru.megboyzz.ui.MainScreen
import ru.megboyzz.ui.MainWindow


fun main(arg: Array<String>) = application {

    val arg1 = "D:\\Projects\\AndroidStudioProjects\\MyDnevnik\\app\\release\\app-release.apk"

    println(if(arg.isNotEmpty()) arg[0] else "")

    MainWindow(
        ::exitApplication
    ){
        Navigator(MainScreen( if(arg.isNotEmpty()) arg[0] else arg1))
    }
}
