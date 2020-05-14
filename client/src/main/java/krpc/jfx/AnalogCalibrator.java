package krpc.jfx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import org.json.simple.JSONObject;

public class AnalogCalibrator extends VBox {
    private final modifiableTextField max;
    private final modifiableTextField p4;
    private final modifiableTextField p3;
    private final modifiableTextField center;
    private final modifiableTextField p2;
    private final modifiableTextField p1;
    private final modifiableTextField min;
    private final ProgressBar progressBar;
    private final TextField current;

    AnalogCalibrator(String nom, JSONObject calibrationData) {
        super();
        setPadding(new Insets(5));
        setSpacing(5);
        Label name = new Label(nom);
        if (calibrationData != null) {
            min = new modifiableTextField((String) calibrationData.get("min"));
            max = new modifiableTextField((String) calibrationData.get("max"));
            center = new modifiableTextField((String) calibrationData.get("center"));
            p1 = new modifiableTextField((String) calibrationData.get("p1"));
            p2 = new modifiableTextField((String) calibrationData.get("p2"));
            p3 = new modifiableTextField((String) calibrationData.get("p3"));
            p4 = new modifiableTextField((String) calibrationData.get("p4"));
        } else {
            max = new modifiableTextField("");
            center = new modifiableTextField("");
            min = new modifiableTextField("");
            p1 = new modifiableTextField("");
            p2 = new modifiableTextField("");
            p3 = new modifiableTextField("");
            p4 = new modifiableTextField("");
        }
        setFormatCheck(center);
        setFormatCheck(min);
        setFormatCheck(max);
        setFormatCheck(p1);
        setFormatCheck(p2);
        setFormatCheck(p3);
        setFormatCheck(p4);

        progressBar = new ProgressBar(0.5);
        /*
        progressBar.setPrefSize(100, 10);
        progressBar.getTransforms().setAll(
                new Translate(30, 100),
                new Rotate(-90, 0, 0)
        );
        */
        current = new TextField("");
        current.setDisable(true);
        getChildren().addAll(name, max, p4, p3, center, p2, p1, min,/* progressBar,*/ current);
    }

    void Reset() {
        if (!min.modified)
            min.setText("");
        if (!max.modified)
            max.setText("");
        if (!center.modified)
            center.setText("");
        current.setText("");
        progressBar.setProgress(0);
    }

    void Update(int newVal) {
        int maxVal = max.getText().equals("") ? newVal : Math.max(newVal, Integer.parseInt(max.getText()));
        int minVal = min.getText().equals("") ? newVal : Math.min(newVal, Integer.parseInt(min.getText()));
        max.setText(String.valueOf(maxVal));
        min.setText(String.valueOf(minVal));
        current.setText(String.valueOf(newVal));
        progressBar.setProgress(((float) newVal - minVal) / (maxVal - minVal));
    }

    JSONObject getJson() {
        //TODO: implémenter une vérification de la cohérence des données
        JSONObject out = new JSONObject();
        out.put("max", max.getText());
        out.put("min", min.getText());
        out.put("center", center.getText());
        out.put("p1", p1.getText());
        out.put("p2", p2.getText());
        out.put("p3", p3.getText());
        out.put("p4", p4.getText());
        return out;
    }

    private class modifiableTextField extends TextField {
        boolean modified = false;

        modifiableTextField(String s) {
            super(s);
        }
    }

    private void setFormatCheck(TextField field) {
        field.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}") || (!newValue.equals("") && Integer.parseInt(newValue) > 255)) {
                field.setText(oldValue);
            }
        });
    }
}
