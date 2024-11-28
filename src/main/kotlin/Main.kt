import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.skiko.SystemTheme
import org.jetbrains.skiko.currentSystemTheme

fun main() = application {
    System.setProperty("skiko.renderApi", "SOFTWARE")
    Window(
        onCloseRequest = ::exitApplication,
        title = "HD Health Check"
    ) {
        App()
    }
}

@Composable
fun App() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Welcome to HD Health Check!")
        }
    }
}
