# HD Health Check

A desktop application built with Compose Desktop to monitor and analyze hard drive health status.

## Features

- Real-time drive space monitoring
- Disk type detection (SSD/HDD)
- Disk space usage visualization
- Material Design 3 modern UI
- Automatic refresh every 5 seconds

### Planned Features

- S.M.A.R.T. data analysis
- Performance metrics tracking
- Health alerts and notifications
- Temperature monitoring
- Disk health prediction

## Tech Stack

- Kotlin
- Compose Desktop
- Material Design 3
- Kotlin Coroutines
- PowerShell (for disk information)

## Development Setup

### Prerequisites

- JDK 17 or later
- Gradle 8.3 or later
- Windows OS (for disk type detection)
- IntelliJ IDEA (recommended) or any IDE with Kotlin support

### Building from Source

1. Clone the repository:
```bash
git clone https://github.com/mundodigitalpro/compose-desktop-hdhealthcheck.git
```

2. Navigate to the project directory:
```bash
cd compose-desktop-hdhealthcheck
```

3. Build the project:
```bash
./gradlew build
```

4. Run the application:
```bash
./gradlew run
```

## Project Structure

```
src/main/kotlin/
├── models/
│   └── DriveInfo.kt          # Data model for drive information
├── services/
│   └── DriveMonitorService.kt # Service for monitoring drives
└── ui/
    └── components/
        └── DriveCard.kt      # UI component for displaying drive info
```

## Features in Detail

### Disk Type Detection
The application uses PowerShell commands to accurately detect whether each drive is an SSD or HDD. This is accomplished by:
- Mapping drive letters to physical disks
- Querying disk media type information
- Caching results for better performance

### Drive Space Monitoring
- Real-time monitoring of drive space usage
- Visual progress indicators
- Space usage in human-readable format
- Automatic refresh every 5 seconds

## Building

To create a distribution:

```bash
./gradlew createDistributable
```

This will create platform-specific distributions in `build/compose/binaries`.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
