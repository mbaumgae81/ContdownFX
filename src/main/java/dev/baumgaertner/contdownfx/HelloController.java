package dev.baumgaertner.contdownfx;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HelloController {
    public Label countdown;
    public DatePicker datePick;
    public Label ZeitError;
    @FXML
    private Label welcomeText;
    @FXML
    private TextField zeit;
    private String zeitEingabe;
    private boolean animRun;


    public void onStartButtonClick(ActionEvent actionEvent) throws InterruptedException {
        zeitEingabe = zeit.getText();

        if (DatumZeitRechner.checkZeitEingabe(zeitEingabe)) {
            ZeitError.setVisible(false);
        } else {
            ZeitError.setVisible(true);
        }


        countDownRun(datePick.getValue());

    }

    public void onExitButtonClick(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void countDownRun(LocalDate zielDatum) throws InterruptedException {
        countdown.setFont(new Font("Arial", 25));
        zeit.setDisable(true);                  // Deaktiviere
        datePick.setDisable(true);              //
        animRun = true;

        new AnimationTimer() {                  // JavaFX Based zur regelmässigen änderungen auf der GUI

            @Override
            public void handle(long l) {
                /**
                 * Schreibe aktuelle restzeit auf Label
                 * bei jedem AnimationTimer durchlauf
                 */
                countdown.setText(DatumZeitRechner.rechne(zielDatum, DatumZeitRechner.stringToTime(zeitEingabe)));


                LocalDateTime temp = DatumZeitRechner.DatumZeitToLDT(zielDatum, DatumZeitRechner.stringToTime(zeitEingabe));

                if (temp.isBefore(LocalDateTime.now())) {       // Ist der Timer Beendet ?
                    stop();

                    countdown.setText(" Ende ");
                }

                if (!animRun) {                             // Prüfe ob per Button gestoppt wurde
                    stop();
                }
            }
        }.start();

    }


    public void onStopButtonClick(ActionEvent event) {

        animRun = false;
        zeit.setDisable(false);                     // Aktiviere die eingabe wieder
        datePick.setDisable(false);                 // DatePick und Zeit

    }

    public void clearZeitEingabe(){
        zeit.setText("");                           // beim Click in das eingabe feld wird der String geleert

    }
}