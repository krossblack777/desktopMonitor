@echo off
REM create Executable jar file.

javac -encoding UTF-8 src/*.java
echo Manifest-Version: 1.0 > MANIFEST.MF
echo Main-Class: Main >> MANIFEST.MF
jar cvfm desktopMonitor.jar MANIFEST.MF -C src .
del *.MF
del src\*.class

