package krpc.jfx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class AnalogCalibrator extends VBox {
    private final TextField max;
    private final TextField min;
    private final TextField center;
    private final ProgressBar progressBar;
    private final TextField current;

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
        int maxVal = max.getText().equals("") ? newVal : Math.max(newVal, Integer.parseInt(max.getText()));
        int minVal = min.getText().equals("") ? newVal : Math.min(newVal, Integer.parseInt(min.getText()));
        max.setText(String.valueOf(maxVal));
        min.setText(String.valueOf(minVal));
        current.setText(String.valueOf(newVal));
        progressBar.setProgress(((float) newVal - minVal) / (maxVal - minVal));
        //TODO: implémenter l'update des affichages
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
