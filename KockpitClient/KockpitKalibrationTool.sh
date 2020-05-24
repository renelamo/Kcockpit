#!/usr/bin/env bash

# mvn package assembly:simple to build the jar
echo "Starting Kockpit Kalibration Tool"
PATH_TO_FX=dependencies/javafx-sdk-14.0.1/lib
LOG_LEVEL=Debug

java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -jar target/KockpitKalibrationTool.jar $LOG_LEVEL