package kockpit.jfx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import java.util.InvalidPropertiesFormatException;

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
            p1.userModified = true;
            p2.userModified = true;
            if (!simple) {
                p3Val = (String) calibrationData.get("p3");
                p4Val = (String) calibrationData.get("p4");
                center = new modifiableTextField((String) calibrationData.get("center"));
                p3 = new modifiableTextField(p3Val);
                p4 = new modifiableTextField(p4Val);
                p3.userModified = true;
                p4.userModified = true;
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

        setAlignment(Pos.CENTER);
        if (simple) {
            Region spacer = new Region();
            setVgrow(spacer, Priority.ALWAYS);
            getChildren().addAll(name, max,spacer,  p2, p1, min, current);
        } else {
            getChildren().addAll(name, max, p4, p3, center, p2, p1, min,/* progressBar,*/ current);
        }
    }

    /**
     * réinitialise tous les champs qui n'ont pas été modifiés par l'utilisateur
     */
    void Reset() {
        if (!min.userModified)
            min.progSetText("");
        if (!max.userModified)
            max.progSetText("");
        if (!p1.userModified)
            p1.progSetText(p1Val);
        if (!p2.userModified)
            p2.progSetText(p2Val);
        if (!simple) {
            if (!p3.userModified)
                p3.progSetText(p3Val);
            if (!p4.userModified)
                p4.progSetText(p4Val);
            if (!center.userModified)
                center.progSetText("");
        }
        current.setText("");
        progressBar.setProgress(0);
    }

    /**
     * Met à jour tous les champs qui n'ont pas été modfiés par l'utilisateur
     * @param newVal la nouelle valeur courante
     */
    void Update(int newVal) {
        int maxVal = max.getText().equals("") ? newVal : Math.max(newVal, Integer.parseInt(max.getText()));
        int minVal = min.getText().equals("") ? newVal : Math.min(newVal, Integer.parseInt(min.getText()));
        max.progSetText(String.valueOf(maxVal));
        min.progSetText(String.valueOf(minVal));
        current.setText(String.valueOf(newVal));
        if (!p1.userModified) {
            p1.progSetText(min.getText());
        }
        if (!simple) {
            if (!p2.userModified) {
                p2.progSetText(center.getText());
            }
            if (!p3.userModified) {
                p3.progSetText(center.getText());
            }
            if (!p4.userModified) {
                p4.progSetText(max.getText());
            }
            if (!center.userModified) {
                center.progSetText(String.valueOf((Integer.parseInt(max.getText()) - Integer.parseInt(min.getText())) / 2));
            }
        } else {

            if (!p2.userModified) {
                p2.progSetText(max.getText());
            }
        }
        progressBar.setProgress(((float) newVal - minVal) / (maxVal - minVal));
    }

    /**
     * Règle les valeurs des poinrs avec l'offset donné par rapport aux valeurs extrêmes
     */
    void autoFillP(int offset) {
        p1.progSetText(String.valueOf(Integer.parseInt(min.getText()) + offset));
        if (!simple) {
            if(!center.userModified){
                center.userModified = true;
                center.progSetText(current.getText()); //on fixCenter, nécessaire à p2/p3 si pas déjà fait
            }
            p2.progSetText(String.valueOf(Integer.parseInt(center.getText()) - offset));
            p3.progSetText(String.valueOf(Integer.parseInt(center.getText()) + offset));
            p4.progSetText(String.valueOf(Integer.parseInt(max.getText()) - offset));
            p3.userModified = true;
            p4.userModified = true;
        } else {
            p2.progSetText(String.valueOf(Integer.parseInt(max.getText()) - offset));
        }
        p1.userModified = true;
        p2.userModified = true;
    }

    /**
     * Fournit les valeurs de tous les TextField à sauvegarder
     * @return Le JSONObject contenant toutes les valeurs
     * @throws InvalidPropertiesFormatException Si il y a incohérence dans les valeurs des points
     */
    JSONObject getJson() throws InvalidPropertiesFormatException{
        JSONObject out = new JSONObject();
        out.put("max", max.getText());
        out.put("min", min.getText());
        int p1int = Integer.parseInt(p1.getText());
        int p2int = Integer.parseInt(p2.getText());
        if(p1int>p2int){
            throw new InvalidPropertiesFormatException("p2 must be greater or equal to p1");
        }
            out.put("p1", String.valueOf(p1int));
            out.put("p2", String.valueOf(p2int));

        if (!simple) {
            int centerInt = Integer.parseInt(center.getText());
            int p3int = Integer.parseInt(p3.getText());
            int p4int = Integer.parseInt(p4.getText());
            if(p3int>p4int){
                throw new InvalidPropertiesFormatException("p4 must be greater or equal to p3");
            }
            if(p3int<centerInt){
                throw new InvalidPropertiesFormatException("p3 must be greater or equal to center");
            }
            if (centerInt<p2int){
                throw new InvalidPropertiesFormatException("p2 must be lower or equal to center");
            }
            out.put("center",String.valueOf(centerInt));
            out.put("p3", String.valueOf(p3int));
            out.put("p4", String.valueOf(p4int));
        } else {
            out.put("p3", "");
        }
        return out;
    }

    /**
     * Remet tous les champs à 0 (ne fonctionne pas pour p1, raison inconnue)
     */
    void ResetAll() {
        min.userModified = false;
        max.userModified = false;
        p1.userModified = false;
        p2.userModified = false;
        if (!simple) {
            p3.userModified = false;
            p4.userModified = false;
            center.userModified = false;
        }
        Reset();
    }

    /**
     * Bloque la valeur 'center' de l'axe sur la valeur actuelle
     */
    void fixCenter() {
        if (!simple) {
            center.progSetText(current.getText());
            center.userModified = true;
        }
    }

    /**
     * Vérifie que le TextField contient bien une chaine représentant un entier entre 0 et 255
     */
    private void setFormatCheck(TextField field) {
        if (field instanceof modifiableTextField && ((modifiableTextField) field).progModified) {
            ((modifiableTextField) field).progModified = false;
            return;
        }
        field.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,3}") ||
                    (!newValue.equals("") && (Integer.parseInt(newValue) > 255 || Integer.parseInt(newValue) < 0))) {
                field.setText(oldValue);
            } else {
                if (field instanceof modifiableTextField) {
                    ((modifiableTextField) field).userModified = true;
                }
            }
        });
    }

    /**
     * Un textField avec une propriété permettant de savoir s'il a été modifié par l'utilisateur ou par le programme
     */
    private static class modifiableTextField extends TextField {
        boolean userModified = false;
        boolean progModified = false;

        modifiableTextField(String s) {
            super(s);
        }

        void progSetText(String s) {
            progModified = true;
            setText(s);
        }
    }
}
