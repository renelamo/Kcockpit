#!/usr/bin/env bash

# mvn package assembly:simple to build the jar
echo "Starting Kockpit Kalibration Tool"
PATH_TO_FX=dependencies/javafx-sdk-14.0.1/lib
# export PATH_TO_FX_MODS=dependencies/javafx-jmods-14.0.1

# java --module-path /usr/share/openjfx/lib --add-modules=javafx.controls,javafx.fxml,javafx.base,javafx.media,javafx.web,javafx.swing -cp target/KockpitClient-1.0.jar:dependencies/krpc-java-0.4.8.jar kockpit.jfx.KockpitCalibrationTool
# java --module-path $PATH_TO_FX --add-modules=javafx.controls,javafx.fxml,javafx.base,javafx.media,javafx.web,javafx.swing -cp target/KockpitClient-1.0.jar kockpit.jfx.KockpitCalibrationTool
java --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml -jar target/KockpitClient-1.0-jar-with-dependencies.jar