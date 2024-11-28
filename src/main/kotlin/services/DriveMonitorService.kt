package services

import models.DriveInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import java.io.File

class DriveMonitorService {
    fun monitorDrives(): Flow<List<DriveInfo>> = flow {
        while (true) {
            val drives = File.listRoots().map { root ->
                DriveInfo(
                    name = root.absolutePath,
                    totalSpace = root.totalSpace,
                    usedSpace = root.totalSpace - root.freeSpace
                )
            }
            emit(drives)
            delay(5000) // Update every 5 seconds
        }
    }
}
