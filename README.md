# Compose Desktop Template

A clean, minimal template for Compose Desktop applications.

## Prerequisites

- Java 17 or higher
- Gradle (will be installed by setup script if not present)

## Setup

1. Run the setup script to verify/install required dependencies:
   ```powershell
   .\setup-dev-env.ps1
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

3. Run the application:
   ```bash
   ./gradlew run
   ```

## Project Structure

- `src/main/kotlin/` - Source files
  - `Main.kt` - Application entry point and main UI
- `build.gradle.kts` - Gradle build configuration
- `settings.gradle.kts` - Gradle settings
- `setup-dev-env.ps1` - Development environment setup script

## Features

- Material 3 Design
- Modern Kotlin and Compose Desktop setup
- Development environment verification script
- Clean project structure

## Building

To create a distribution:

```bash
./gradlew createDistributable
```

This will create platform-specific distributions in `build/compose/binaries`.
