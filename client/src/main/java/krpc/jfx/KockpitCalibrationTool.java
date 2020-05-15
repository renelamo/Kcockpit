package krpc.jfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import krpc.client.RPCException;
import krpc.core.KRPCClient;
import krpc.core.UnknownOSException;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

public class KockpitCalibrationTool extends Application {
    private final KRPCClient client = new KRPCClient();
    private final LinkedHashMap<String, AnalogCalibrator> calibrators = new LinkedHashMap<>(8);

    private final Thread updateValues = new Thread(() -> {
        client.logger.INFO("Démarrage du thread de communication série");
        try {
            client.connectSerial();
            do {
                try {
                    calibrators.forEach((axisName, analogCalibrator) -> {
                        try {
                            Method getValue = client.commManager.getClass().getMethod("get" + StringUtils.capitalize(axisName));
                            analogCalibrator.Update((Integer) getValue.invoke(client.commManager));
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
                    client.connectSerial();
                }
            } while (true);
        }
        catch (UnknownOSException e) {
            client.logger.ERROR("OS non reconnu");
        }
        catch (InterruptedException e) {
            client.logger.INFO("Interruption du thread de communication série");
            Thread.currentThread().interrupt();
        }
    });

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        calibrators.put("throttle", null);
        calibrators.put("pitch", null);
        calibrators.put("yaw", null);
        calibrators.put("roll", null);
        calibrators.put("x", null);
        calibrators.put("y", null);
        calibrators.put("z", null);
        calibrators.put("t", null);

        //region left
        VBox leftVBox = new VBox(
                new Label("Axe:"),
                new Label("Valeur max:"),
                new Label("p4"),
                new Label("p3"),
                new Label("Valeur centrale:"),
                new Label("p2"),
                new Label("p1"),
                new Label("Valeur min:"),
                new Label("Valeur actuelle:")
        );
        leftVBox.setSpacing(14.5);
        leftVBox.setPadding(new Insets(5));
        root.setLeft(leftVBox);
        //endregion

        //TODO: gérer Throttle
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
        Button resetAll = new Button("Reset everything");
        resetAll.setOnAction(event -> calibrators.forEach((s, analogCalibrator) -> analogCalibrator.ResetAll()));
        Button quit = new Button("Exit");
        quit.setOnAction((actionEvent) -> {
            updateValues.interrupt();
            primaryStage.close();
        });
        Button save = new Button("Save");
        save.setOnAction((actionEvent) -> {
            client.logger.INFO("Sauvegarde des valeurs de calibration");
            JSONObject toWrite = new JSONObject();

            calibrators.forEach((name, analogCalibrator) -> toWrite.put(name, analogCalibrator.getJson()));

            try (FileWriter file = new FileWriter("kalibration.json")) {
                file.write(toWrite.toJSONString());
                file.flush();
            }
            catch (IOException e) {
                client.logger.ERROR("Impossible d'écrire dans le fichier de sauvegarde");
            }
        });
        Button setP = new Button("Autoset Point Values");
        setP.setOnAction(event -> calibrators.forEach((s, analogCalibrator) -> analogCalibrator.autoFillP(10)));
        Button setC = new Button("Fix center values");
        setC.setOnAction(event -> calibrators.forEach((s, analogCalibrator) -> analogCalibrator.fixCenter()));
        HBox bottom = new HBox(
                setP,
                setC,
                reset,
                resetAll,
                save,
                quit
        );
        bottom.setSpacing(10);
        bottom.setPadding(new Insets(5));
        root.setBottom(bottom);
        //endregion

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Kockpit Calibration Tool");
        primaryStage.setOnCloseRequest((windowEvent) -> updateValues.interrupt());
        primaryStage.show();
        updateValues.start();
    }
}
