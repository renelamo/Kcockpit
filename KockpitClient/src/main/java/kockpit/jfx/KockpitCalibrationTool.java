package kockpit.jfx;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kockpit.core.KRPCClient;
import kockpit.core.Logger;
import kockpit.core.UnknownOSException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedHashMap;
import java.util.List;

public class KockpitCalibrationTool extends Application {
    private KRPCClient client;
    private final LinkedHashMap<String, AnalogCalibrator> calibrators = new LinkedHashMap<>(8);
    //L'ensemble des Calibrators, classés par nom d'axe

    private final LinkedHashMap<String, MutableInt> mutableInts = new LinkedHashMap<>(8);
    //Les mutableInt servent à échanger des valeurs entières entre les threads, claassés par nom d'axe

    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            //Appelée à chaque frame, pour actualiser les valeurs affichées
            calibrators.forEach((s, analogCalibrator) -> analogCalibrator.Update(mutableInts.get(s).getValue()));
        }
    };

    //Thread de récupéraion des valeurs sur la Série
    private final Thread updateValues = new Thread() {
        volatile boolean initiated = false;
        volatile boolean interrupted = false;

        @Override
        public void start() {
            super.start();
            client.logger.DEBUG("Démarrage du thread de communication série");
        }

        @Override
        public void run() {
            do {
                if (!initiated) {
                    try {
                        client.connectSerial();
                        initiated = true;
                    }
                    catch (UnknownOSException e) {
                        client.logger.stream.println();
                        client.logger.ERROR("OS inconnu");
                        Thread.currentThread().interrupt();
                    }
                    catch (InterruptedException e) {
                        client.logger.stream.println();
                        Thread.currentThread().interrupt();
                        break;
                    }
                }

                try {
                    calibrators.forEach((axisName, analogCalibrator) -> {
                        try {
                            Method getValue = client.commManager.getClass().getMethod("get" + StringUtils.capitalize(axisName));
                            //analogCalibrator.Update((Integer) getValue.invoke(client.commManager));
                            mutableInts.get(axisName).setValue((Integer) getValue.invoke(client.commManager));
                        }
                        catch (NoSuchMethodException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e) {
                            if (e.getCause() instanceof IOException) {
                                throw new UncheckedIOException((IOException) e.getCause());
                            } else {
                                e.getCause().printStackTrace();
                            }
                        }
                    });
                }
                catch (UncheckedIOException e) {
                    client.logger.WARNING("Panneau déconnecté, tentative de reconnection");
                    initiated = false;
                }
            } while (!interrupted);
        }

        @Override
        public void interrupt() {
            if(!interrupted) {
                client.logger.DEBUG("Interruption du thread de communication série");
            }
            interrupted = true;
            super.interrupt();
        }
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        List<String> parameters = getParameters().getRaw();
        if (parameters.size()>0) {
            client = new KRPCClient(Logger.LogLevel.valueOf(parameters.get(0)));
        } else {
            client = new KRPCClient();
        }
        BorderPane root = new BorderPane();

        //region hashMaps
        //initialisation des hashmaps
        calibrators.put("throttle", null);
        calibrators.put("pitch", null);
        calibrators.put("yaw", null);
        calibrators.put("roll", null);
        calibrators.put("x", null);
        calibrators.put("y", null);
        calibrators.put("z", null);
        calibrators.put("t", null);
        mutableInts.put("throttle", new MutableInt(0));
        mutableInts.put("pitch", new MutableInt(0));
        mutableInts.put("yaw", new MutableInt(0));
        mutableInts.put("roll", new MutableInt(0));
        mutableInts.put("x", new MutableInt(0));
        mutableInts.put("y", new MutableInt(0));
        mutableInts.put("z", new MutableInt(0));
        mutableInts.put("t", new MutableInt(0));
        //endregion

        //region left
        VBox leftVBox = new VBox(
                new Label("Axe:"),
                new Label("Valeur max:"),
                new Label("p4:"),
                new Label("p3:"),
                new Label("Valeur centrale:"),
                new Label("p2:"),
                new Label("p1:"),
                new Label("Valeur min:"),
                new Label("Valeur actuelle:")
        );
        leftVBox.setAlignment(Pos.CENTER_LEFT);
        leftVBox.setSpacing(14.5);
        leftVBox.setPadding(new Insets(5));
        root.setLeft(leftVBox);
        //endregion

        //region center
        JSONObject calibrations = client.getCalibration();
        if (calibrations != null) {
            calibrators.forEach((name, analogCalibrator) ->
                    calibrators.put(name, new AnalogCalibrator(StringUtils.capitalize(name), (JSONObject) calibrations.get(name))));
        } else {
            calibrators.forEach((name, analogCalibrator) ->
                    calibrators.put(name, new AnalogCalibrator(StringUtils.capitalize(name), null)));
        }

        HBox centerHBox = new HBox();
        calibrators.forEach((s, analogCalibrator) -> centerHBox.getChildren().add(analogCalibrator));
        root.setCenter(centerHBox);
        //endregion

        //region bottom
        Button reset = new Button("Reset");
        reset.setOnAction((actionEvent -> calibrators.forEach((name, analogCalibrator) -> analogCalibrator.Reset())));
        reset.setCancelButton(true);

        Button resetAll = new Button("Reset everything");
        resetAll.setOnAction(event -> calibrators.forEach((s, analogCalibrator) -> analogCalibrator.ResetAll()));

        Button quit = new Button("Exit");
        quit.setOnAction((actionEvent) -> {
            updateValues.interrupt();
            primaryStage.close();
        });

        Button save = new Button("Save");
        save.setDefaultButton(true);
        save.setOnAction((actionEvent) -> {
            client.logger.INFO("Sauvegarde des valeurs de calibration");
            JSONObject toWrite = new JSONObject();

            calibrators.forEach((name, analogCalibrator) -> {
                try {
                    toWrite.put(name, analogCalibrator.getJson());
                    try (FileWriter file = new FileWriter("kalibration.json")) {
                        file.write(toWrite.toJSONString());
                        file.flush();
                    }
                    catch (IOException e) {
                        client.logger.ERROR("Impossible d'écrire dans le fichier de sauvegarde");
                    }
                }
                catch (InvalidPropertiesFormatException e) {
                    client.logger.ERROR(e.getMessage());
                }
            });


        });

        Button setP = new Button("Autoset Point Values");
        setP.setOnAction(event -> {
            Stage popup = new Stage();
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.initOwner(primaryStage);
            TextField textInput = new TextField("10");
            textInput.textProperty().addListener((observableValue, oldValue, newValue) -> {
                if (!newValue.matches("\\d{0,3}") || (!newValue.equals("") && Integer.parseInt(newValue) > 255)) {
                    textInput.setText(oldValue);
                }
            });
            Button okButton = new Button("OK");
            okButton.setDefaultButton(true);
            okButton.setOnAction(event1 -> {
                if (textInput.getText().equals("")) {
                    return;
                }
                int offset = Integer.parseInt(textInput.getText());
                calibrators.forEach((s, analogCalibrator) -> analogCalibrator.autoFillP(offset));
                popup.hide();
            });
            Button cancelButton = new Button("Cancel");
            cancelButton.setCancelButton(true);
            cancelButton.setOnAction(event1 -> popup.hide());
            GridPane pane = new GridPane();
            pane.setHgap(5);
            pane.setVgap(5);
            pane.setPadding(new Insets(5));
            pane.add(new Label("Points offset:"), 0, 0);
            pane.add(textInput, 1, 0);
            pane.add(cancelButton, 0, 1);
            GridPane.setHalignment(okButton, HPos.RIGHT);
            pane.getColumnConstraints().addAll(new ColumnConstraints(100), new ColumnConstraints(50));
            pane.add(okButton, 1, 1);
            popup.setScene(new Scene(pane));
            popup.show();
        });

        Button setC = new Button("Fix center values");
        setC.setOnAction(event -> calibrators.forEach((s, analogCalibrator) -> analogCalibrator.fixCenter()));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottom = new HBox(
                setP,
                setC,
                reset,
                resetAll,
                spacer,
                save,
                quit
        );
        bottom.setSpacing(10);
        bottom.setPadding(new Insets(5));
        root.setBottom(bottom);
        //endregion

        Scene scene = new Scene(root, 800, 310);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Kockpit Calibration Tool");
        primaryStage.setOnCloseRequest((windowEvent) -> updateValues.interrupt());
        primaryStage.setResizable(false);
        updateValues.start();
        animationTimer.start();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        updateValues.interrupt();
        super.stop();
    }
}
