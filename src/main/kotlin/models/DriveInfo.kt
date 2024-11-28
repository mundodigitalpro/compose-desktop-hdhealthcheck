package models

data class DriveInfo(
    val name: String,
    val totalSpace: Long,
    val usedSpace: Long
) {
    val usedSpacePercentage: Int
        get() = ((usedSpace.toDouble() / totalSpace.toDouble()) * 100).toInt()
}
