package services

import models.DriveInfo
import models.DiskType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import java.io.File
import java.io.BufferedReader
import java.io.InputStreamReader

class DriveMonitorService {
    private val diskTypeCache = mutableMapOf<String, DiskType>()
    
    private fun getDiskType(driveLetter: String): DiskType {
        // Return cached value if available
        diskTypeCache[driveLetter]?.let { return it }
        
        try {
            val cleanDriveLetter = driveLetter.replace(":\\", "")
            val command = """
                Get-PhysicalDisk | ForEach-Object {
                    ${'$'}partition = Get-Partition | Where-Object { ${'$'}_.DriveLetter -eq '$cleanDriveLetter' }
                    if (${'$'}partition) {
                        ${'$'}disk = Get-Disk | Where-Object { ${'$'}_.Number -eq ${'$'}partition.DiskNumber }
                        if (${'$'}disk) {
                            ${'$'}_.MediaType
                        }
                    }
                }
            """.trimIndent()
            
            val process = Runtime.getRuntime().exec(arrayOf(
                "powershell.exe",
                "-NoProfile",
                "-Command",
                command
            ))
            
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            
            reader.useLines { lines ->
                for (line in lines) {
                    if (line.isNotBlank()) {
                        val trimmedLine = line.trim().lowercase()
                        val type = when {
                            trimmedLine.contains("ssd") -> DiskType.SSD
                            trimmedLine.contains("hdd") || trimmedLine.contains("unspecified") -> DiskType.HDD
                            else -> DiskType.UNKNOWN
                        }
                        // Cache the result
                        diskTypeCache[driveLetter] = type
                        return type
                    }
                }
            }
            
            // Wait for the process to complete
            process.waitFor()
            
        } catch (e: Exception) {
            println("Error detecting disk type: ${e.message}")
        }
        
        return DiskType.UNKNOWN.also { diskTypeCache[driveLetter] = it }
    }

    fun monitorDrives(): Flow<List<DriveInfo>> = flow {
        while (true) {
            val drives = File.listRoots().map { root ->
                DriveInfo(
                    name = root.absolutePath,
                    totalSpace = root.totalSpace,
                    usedSpace = root.totalSpace - root.freeSpace,
                    diskType = getDiskType(root.absolutePath)
                )
            }
            emit(drives)
            delay(5000) // Update every 5 seconds
        }
    }
}
