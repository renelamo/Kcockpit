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

public class KockpitCalibrationTool extends Application {
    private final AnalogCalibrator throttleCalibrator = new AnalogCalibrator("Throttle");
    private final AnalogCalibrator pitchCalibrator = new AnalogCalibrator("Pitch");
    private final AnalogCalibrator yawCalibrator = new AnalogCalibrator("Yaw");
    private final AnalogCalibrator rollCalibrator = new AnalogCalibrator("Roll");
    private final AnalogCalibrator xCalibrator = new AnalogCalibrator("X");
    private final AnalogCalibrator yCalibrator = new AnalogCalibrator("Y");
    private final AnalogCalibrator zCalibrator = new AnalogCalibrator("Z");
    private final AnalogCalibrator tCalibrator = new AnalogCalibrator("T");
    private final Thread updateValues = new Thread(() -> {
        KRPCClient client = new KRPCClient();
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
                new Label("Valeur min:"),
                new Label("Valeur centrale:"),
                new Label("Valeur actuelle:")
        );
        leftVBox.setSpacing(15);
        leftVBox.setPadding(new Insets(5));
        root.setLeft(leftVBox);
        //endregion

        //region center
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
            throttleCalibrator.Reset();
            pitchCalibrator.Reset();
            yawCalibrator.Reset();
            rollCalibrator.Reset();
            xCalibrator.Reset();
            yCalibrator.Reset();
            zCalibrator.Reset();
            tCalibrator.Reset();
        }));
        Button quit = new Button("Exit");
        quit.setOnAction((actionEvent) -> {
            updateValues.interrupt();
            primaryStage.close();
        });
        Button save = new Button("Save");
        //TODO: implémenter la sauvegarde des valeurs
        HBox bottom = new HBox(save, reset, quit);
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
