package krpc.jfx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import krpc.core.CommTable;

public class AnalogCalibrator extends VBox {
    private TextField max;
    private TextField min;
    private TextField center;
    private ProgressBar progressBar;

    AnalogCalibrator(String nom) {
        super(new Label(nom));
        setPadding(new Insets(5));
        max = new TextField("max");
        min = new TextField("min");
        center = new TextField("center");
        center.setDisable(true);
        progressBar = new ProgressBar(0.5);
        progressBar.setPrefSize(100, 10);
        progressBar.getTransforms().setAll(
                new Translate(0, 100),
                new Rotate(-90, 0, 0)
        );
        getChildren().addAll(max, min, center, progressBar);
    }

    void Update(int newVal) {

//TODO: implémenter l'update des affichages
    }
}
