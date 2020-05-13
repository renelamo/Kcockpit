package krpc.jfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import krpc.core.KRPCClient;

public class KockpitCalibrationTool extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Kockpit Calibration Tool");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        AnalogCalibrator pitchCalibrator = new AnalogCalibrator("Pitch");
        root.setLeft(pitchCalibrator);

        Button reset = new Button("Reset");
        Button quit = new Button("Exit");
        HBox bottom = new HBox(reset, quit);
        root.setBottom(bottom);
        primaryStage.show();

        Thread updateValues = new Thread(() -> {
            try {
                KRPCClient client = new KRPCClient();
                client.connectSerial();
                pitchCalibrator.Update(client.commManager.getPitch());
            }
            catch (InterruptedException e) {
                System.out.println("Interrupting Thread");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        updateValues.start();
        primaryStage.setOnCloseRequest((windowEvent) -> {
            updateValues.interrupt();
        });
        //TODO: implémenter l'actualisation des valeurs d'affichage
    }
}
