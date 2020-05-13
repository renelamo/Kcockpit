package krpc.jfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import krpc.core.CommTable;

public class AnalogCalibrator extends VBox {
    private TextField max;
    private int maxVal;
    private TextField min;
    private int minVal;
    private TextField center;
    private int centerVal;
    private ProgressBar progressBar;
    private TextField current;

    AnalogCalibrator(String nom) {
        super();
        Label name = new Label(nom);
        name.getTransforms().setAll(
                new Translate(30, 0)
        );
        setPadding(new Insets(5));
        setSpacing(5);
        max = new TextField("");
        setFormatCheck(max);
        min = new TextField("");
        setFormatCheck(min);
        center = new TextField("");
        setFormatCheck(center);
        progressBar = new ProgressBar(0.5);
        progressBar.setPrefSize(100, 10);
        progressBar.getTransforms().setAll(
                new Translate(30, 100),
                new Rotate(-90, 0, 0)
        );
        current = new TextField("");
        current.setDisable(true);
        getChildren().addAll(name, max, min, center,/* progressBar,*/ current);
    }

    void Update(int newVal) {
        if (max.getText().equals("")) {
            max.setText(String.valueOf(newVal));
        } else {
            max.setText(String.valueOf(Math.max(newVal, Integer.parseInt(max.getText()))));
        }
        if (min.getText().equals("")) {
            min.setText(String.valueOf(newVal));
        } else {
            min.setText(String.valueOf(Math.min(newVal, Integer.parseInt(max.getText()))));
        }
        current.setText(String.valueOf(newVal));
//TODO: implémenter l'update des affichages
        progressBar.setProgress(((float) newVal - minVal) / (maxVal - minVal));
    }

    void Reset() {
        min.setText("");
        max.setText("");
        center.setText("");
        progressBar.setProgress(0);
    }

    private void setFormatCheck(TextField field) {
        field.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}") || (!newValue.equals("") && Integer.parseInt(newValue) > 255)) {
                field.setText(oldValue);
            }
        });
    }
}
