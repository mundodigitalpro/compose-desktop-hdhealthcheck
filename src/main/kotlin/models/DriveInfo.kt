package models

enum class DiskType {
    HDD,
    SSD,
    UNKNOWN
}

data class DriveInfo(
    val name: String,
    val totalSpace: Long,
    val usedSpace: Long,
    val diskType: DiskType
) {
    val usedSpacePercentage: Int
        get() = ((usedSpace.toDouble() / totalSpace.toDouble()) * 100).toInt()
}
