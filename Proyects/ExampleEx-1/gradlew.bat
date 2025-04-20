@echo off
REM Gradle startup script for Windows

SET DIRNAME=%~dp0
SET DIRNAME=%DIRNAME:~0,-1%

SET JAVA_HOME=C:\Program Files\Java\jdk1.8.0_251
SET PATH=%JAVA_HOME%\bin;%PATH%

SET GRADLE_USER_HOME=%DIRNAME%\.gradle

IF NOT EXIST "%DIRNAME%\gradle\wrapper\gradle-wrapper.jar" (
    echo "Gradle wrapper jar not found. Please run 'gradlew' from the command line."
    exit /b 1
)

java -classpath "%DIRNAME%\gradle\wrapper\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*