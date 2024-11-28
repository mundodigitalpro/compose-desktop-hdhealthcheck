# Development Environment Setup Script for Compose Desktop Template
# This script will set up both Java and Gradle environments

$gradleVersion = "8.3"
$javaVersion = "17.0.13.11"
$javaHome = "C:\Program Files\Eclipse Adoptium\jdk-$javaVersion-hotspot"
$gradleHome = "C:\gradle"
$downloadUrl = "https://services.gradle.org/distributions/gradle-$gradleVersion-bin.zip"
$zipFile = "$gradleHome\gradle-$gradleVersion-bin.zip"

function Write-Status {
    param(
        [string]$Message,
        [string]$Type = "Info" # Info, Success, Error, Warning
    )
    
    $color = switch ($Type) {
        "Success" { "Green" }
        "Error" { "Red" }
        "Warning" { "Yellow" }
        default { "Cyan" }
    }
    
    Write-Host $Message -ForegroundColor $color
}

# Function to check Java version
function Test-JavaInstallation {
    try {
        $javaHome = [System.Environment]::GetEnvironmentVariable("JAVA_HOME", "User")
        if ($javaHome -and (Test-Path "$javaHome\bin\java.exe")) {
            $javaCmd = "$javaHome\bin\java.exe"
        } else {
            $javaCmd = "java"
        }

        $javaOutput = & $javaCmd -version 2>&1 | Out-String
        $javaOutput = $javaOutput.Trim()
        
        if ($javaOutput -match 'version "(\d+)') {
            $majorVersion = $matches[1]
            return $majorVersion
        }
    }
    catch {
        return $null
    }
}

# Function to install Gradle
function Install-Gradle {
    Write-Status "Setting up Gradle environment..." "Info"
    
    if (-not (Test-Path $gradleHome)) {
        New-Item -ItemType Directory -Path $gradleHome | Out-Null
    }
    
    if (-not (Test-Path $zipFile)) {
        Write-Status "Downloading Gradle..." "Info"
        Invoke-WebRequest -Uri $downloadUrl -OutFile $zipFile
    }
    
    if (-not (Test-Path "$gradleHome\gradle-$gradleVersion")) {
        Write-Status "Extracting Gradle..." "Info"
        Expand-Archive -Path $zipFile -DestinationPath $gradleHome
    }
    
    # Set GRADLE_HOME environment variable
    [System.Environment]::SetEnvironmentVariable("GRADLE_HOME", "$gradleHome\gradle-$gradleVersion", "User")
    
    # Add Gradle to PATH
    $path = [System.Environment]::GetEnvironmentVariable("Path", "User")
    if (-not $path.Contains("$gradleHome\gradle-$gradleVersion\bin")) {
        [System.Environment]::SetEnvironmentVariable("Path", "$path;$gradleHome\gradle-$gradleVersion\bin", "User")
    }
    
    Write-Status "Gradle setup completed successfully!" "Success"
}

# Main script execution
Write-Status "Checking development environment..." "Info"

# Check Java installation
$currentJavaVersion = Test-JavaInstallation
if (-not $currentJavaVersion -or $currentJavaVersion -lt 17) {
    Write-Status "Java 17 or higher is required but not found." "Error"
    Write-Status "Please install Java 17 from: https://adoptium.net/" "Info"
    exit 1
}
Write-Status "Java $currentJavaVersion detected." "Success"

# Check Gradle installation
if (-not (Get-Command gradle -ErrorAction SilentlyContinue)) {
    Write-Status "Gradle not found. Installing..." "Warning"
    Install-Gradle
} else {
    Write-Status "Gradle installation detected." "Success"
}

Write-Status "Development environment setup completed!" "Success"
