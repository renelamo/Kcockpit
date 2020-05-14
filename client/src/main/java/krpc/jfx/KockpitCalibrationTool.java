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
import krpc.core.KRPCClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class KockpitCalibrationTool extends Application {
    private final KRPCClient client = new KRPCClient();
    private AnalogCalibrator throttleCalibrator;
    private AnalogCalibrator pitchCalibrator;
    private AnalogCalibrator yawCalibrator;
    private AnalogCalibrator rollCalibrator;
    private AnalogCalibrator xCalibrator;
    private AnalogCalibrator yCalibrator;
    private AnalogCalibrator zCalibrator;
    private AnalogCalibrator tCalibrator;
    private AnalogCalibrator[] calibrators = {
            throttleCalibrator,
            pitchCalibrator,
            yawCalibrator,
            rollCalibrator,
            xCalibrator,
            yCalibrator,
            zCalibrator,
            tCalibrator
    };
    private final Thread updateValues = new Thread(() -> {
        client.logger.INFO("Démarrage du thread de communication série");
        try {
            client.connectSerial();
            //TODO: implémenter l'actualisation des valeurs d'affichage en boucle
            pitchCalibrator.Update(client.commManager.getPitch());
        }
        catch (InterruptedException e) {
            System.out.println();
            client.logger.INFO("Interruption du thread de communication série");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    });

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

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
        leftVBox.setSpacing(15);
        leftVBox.setPadding(new Insets(5));
        root.setLeft(leftVBox);
        //endregion

        //TODO: gérer Throttle
        //region center
        JSONObject calibrations = client.getCalibration();
        throttleCalibrator = new AnalogCalibrator("Throttle", (JSONObject) calibrations.get("throttle"), true);
        pitchCalibrator = new AnalogCalibrator("Pitch", (JSONObject) calibrations.get("pitch"));
        yawCalibrator = new AnalogCalibrator("Yaw", (JSONObject) calibrations.get("yaw"));
        rollCalibrator = new AnalogCalibrator("Roll", (JSONObject) calibrations.get("roll"));
        xCalibrator = new AnalogCalibrator("X", (JSONObject) calibrations.get("x"));
        yCalibrator = new AnalogCalibrator("Y", (JSONObject) calibrations.get("y"));
        zCalibrator = new AnalogCalibrator("Z", (JSONObject) calibrations.get("z"));
        tCalibrator = new AnalogCalibrator("T", (JSONObject) calibrations.get("t"));
        root.setCenter(new HBox(
                throttleCalibrator,
                pitchCalibrator,
                yawCalibrator,
                rollCalibrator,
                xCalibrator,
                yCalibrator,
                zCalibrator,
                tCalibrator
        ));
        //endregion

        //region bottom
        Button reset = new Button("Reset");
        reset.setOnAction((actionEvent -> {
            for (AnalogCalibrator cal : calibrators) {
                cal.Reset();
            }
        }));
        Button quit = new Button("Exit");
        quit.setOnAction((actionEvent) -> {
            updateValues.interrupt();
            primaryStage.close();
        });
        Button save = new Button("Save");
        save.setOnAction((actionEvent) -> {
            client.logger.INFO("Sauvegarde des valeurs de calibration");
            JSONObject toWrite = new JSONObject();
            toWrite.put("pitch", pitchCalibrator.getJson());
            toWrite.put("yaw", yawCalibrator.getJson());
            toWrite.put("roll", rollCalibrator.getJson());
            toWrite.put("x", xCalibrator.getJson());
            toWrite.put("y", yCalibrator.getJson());
            toWrite.put("z", zCalibrator.getJson());
            toWrite.put("t", tCalibrator.getJson());

            try (FileWriter file = new FileWriter("kalibration.json")) {
                file.write(toWrite.toJSONString());
                file.flush();
            }
            catch (IOException e) {
                client.logger.ERROR("Impossible d'écrire dans le fichier de sauvegarde");
            }
        });
        Button setP = new Button("Autoset Point Values");
        setP.setOnAction(event -> {
            for (AnalogCalibrator cal : calibrators) {
                cal.autoFillP(10);
            }
        });
        Button setC = new Button("Fix center values");
        setC.setOnAction(event -> {
            for (AnalogCalibrator cal : calibrators) {
                cal.fixCenter();
            }
        });

        HBox bottom = new HBox(
                setP,
                setC,
                save,
                reset,
                quit
        );
        bottom.setSpacing(10);
        bottom.setPadding(new Insets(5));
        root.setBottom(bottom);
        //endregion

        Scene scene = new Scene(root, 800, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Kockpit Calibration Tool");
        primaryStage.setOnCloseRequest((windowEvent) -> updateValues.interrupt());
        primaryStage.show();
        updateValues.start();
    }
}
