package krpc.jfx;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
//import javafx.scene.transform.Rotate;
//import javafx.scene.transform.Translate;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

public class AnalogCalibrator extends VBox {
    private final modifiableTextField max;
    private final boolean simple;
    private modifiableTextField p4 = null;
    private modifiableTextField p3 = null;
    private final modifiableTextField p2;
    private final modifiableTextField p1;
    private final modifiableTextField min;
    private final ProgressBar progressBar;
    private final TextField current;
    private String p1Val, p2Val, p3Val, p4Val;
    private modifiableTextField center;

    AnalogCalibrator(String nom, JSONObject calibrationData) {
        super();
        simple = StringUtils.capitalize(nom).equals("Throttle");
        setPadding(new Insets(5));
        setSpacing(5);
        Label name = new Label(nom);
        if (calibrationData != null) {
            min = new modifiableTextField((String) calibrationData.get("min"));
            max = new modifiableTextField((String) calibrationData.get("max"));
            p1Val = (String) calibrationData.get("p1");
            p2Val = (String) calibrationData.get("p2");
            p1 = new modifiableTextField(p1Val);
            p2 = new modifiableTextField(p2Val);
            if (!simple) {
                p3Val = (String) calibrationData.get("p3");
                p4Val = (String) calibrationData.get("p4");
                center = new modifiableTextField((String) calibrationData.get("center"));
                p3 = new modifiableTextField(p3Val);
                p4 = new modifiableTextField(p4Val);
            }
        } else {
            max = new modifiableTextField("");
            center = new modifiableTextField("");
            min = new modifiableTextField("");
            p1 = new modifiableTextField("");
            p2 = new modifiableTextField("");
            p3 = new modifiableTextField("");
            p4 = new modifiableTextField("");
            setFormatCheck(p3);
            setFormatCheck(p4);
            setFormatCheck(center);
        }
        setFormatCheck(min);
        setFormatCheck(max);
        setFormatCheck(p1);
        setFormatCheck(p2);

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
        if (simple) {
            getChildren().addAll(name, max, p2, p1, min, current);
        } else {
            getChildren().addAll(name, max, p4, p3, center, p2, p1, min,/* progressBar,*/ current);
        }
    }

    void Reset() {
        if (!min.modified)
            min.setText("");
        if (!max.modified)
            max.setText("");
        if (!p1.modified)
            p1.setText(p1Val);
        if (!p2.modified)
            p2.setText(p2Val);
        if (!simple) {
            if (!p3.modified)
                p3.setText(p3Val);
            if (!p4.modified)
                p4.setText(p4Val);
            if (!center.modified)
                center.setText("");
        }
        current.setText("");
        progressBar.setProgress(0);
    }

    void Update(int newVal) {
        int maxVal = max.getText().equals("") ? newVal : Math.max(newVal, Integer.parseInt(max.getText()));
        int minVal = min.getText().equals("") ? newVal : Math.min(newVal, Integer.parseInt(min.getText()));
        max.setText(String.valueOf(maxVal));
        min.setText(String.valueOf(minVal));
        current.setText(String.valueOf(newVal));
        if (!p1.modified) {
            p1.setText(min.getText());
        }
        if (!p2.modified) {
            p2.setText(center.getText());
        }
        if (!simple) {
            if (!p3.modified) {
                p3.setText(center.getText());
            }
            if (!p4.modified) {
                p4.setText(max.getText());
            }
            if (!center.modified) {
                center.setText(String.valueOf((Integer.parseInt(max.getText()) - Integer.parseInt(min.getText())) / 2));
            }
        }
        progressBar.setProgress(((float) newVal - minVal) / (maxVal - minVal));
    }

    void autoFillP(int offset) {
        if (!p1.modified)
            p1.setText(String.valueOf(Integer.parseInt(min.getText()) + offset));
        if (!simple) {
            if (!p2.modified)
                p2.setText(String.valueOf(Integer.parseInt(center.getText()) - offset));
            if (!p3.modified)
                p3.setText(String.valueOf(Integer.parseInt(center.getText()) + offset));
            if (!p4.modified)
                p4.setText(String.valueOf(Integer.parseInt(max.getText()) - offset));
        } else {
            if (!p2.modified)
                p2.setText(String.valueOf(Integer.parseInt(max.getText()) - offset));
        }
    }

    JSONObject getJson() {
        //TODO: implémenter une vérification de la cohérence des données
        JSONObject out = new JSONObject();
        out.put("max", max.getText());
        out.put("min", min.getText());
        out.put("p1", p1.getText());
        out.put("p2", p2.getText());
        if (!simple) {
            out.put("center", center.getText());
            out.put("p3", p3.getText());
            out.put("p4", p4.getText());
        } else {
            out.put("p3", "");
        }
        return out;
    }

    void ResetAll() {
        min.modified = false;
        max.modified = false;
        p1.modified = false;
        p2.modified = false;
        if (!simple) {
            p3.modified = false;
            p4.modified = false;
            center.modified = false;
        }
        Reset();
    }

    void fixCenter() {
        if (!simple)
            center.modified = true;
    }

    private void setFormatCheck(TextField field) {
        field.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}") || (!newValue.equals("") && Integer.parseInt(newValue) > 255)) {
                field.setText(oldValue);
            } else {
                if (field instanceof modifiableTextField) {
                    ((modifiableTextField) field).modified = true;
                }
            }
        });
    }

    private static class modifiableTextField extends TextField {
        boolean modified = false;

        modifiableTextField(String s) {
            super(s);
        }
    }
}
