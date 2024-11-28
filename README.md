# HD Health Check

A desktop application built with Compose Desktop to monitor and analyze hard drive health status.

## Features (Planned)

- Hard drive health status monitoring
- S.M.A.R.T. data analysis
- Disk space usage visualization
- Performance metrics tracking
- Health alerts and notifications

## Tech Stack

- Kotlin
- Compose Desktop
- Material Design 3
- Kotlin Coroutines

## Development Setup

### Prerequisites

- JDK 17 or later
- Gradle 8.3 or later
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

- `src/main/kotlin/` - Source files
  - `Main.kt` - Application entry point and main UI
- `build.gradle.kts` - Gradle build configuration
- `settings.gradle.kts` - Gradle settings
- `setup-dev-env.ps1` - Development environment setup script

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
