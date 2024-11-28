import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import services.DriveMonitorService
import ui.components.DriveCard
import models.DriveInfo

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
    val driveMonitorService = remember { DriveMonitorService() }
    val scope = rememberCoroutineScope()
    var drives by remember { mutableStateOf<List<DriveInfo>>(emptyList()) }
    
    LaunchedEffect(Unit) {
        scope.launch {
            driveMonitorService.monitorDrives().collect { driveList ->
                drives = driveList
            }
        }
    }
    
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "HD Health Check",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                drives.forEach { drive ->
                    DriveCard(driveInfo = drive)
                }
            }
        }
    }
}
