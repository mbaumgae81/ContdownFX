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
        System.out.println(" im countdown");
        System.out.println(!DatumZeitRechner.DatumPlusZeit(zielDatum, DatumZeitRechner.stringToTime(zeitEingabe)).isNegative());

        countdown.setFont(new Font("Arial", 25));
        zeit.setDisable(true);
        datePick.setDisable(true);
        animRun = true;

        new AnimationTimer() {

            @Override
            public void handle(long l) {

                countdown.setText(DatumZeitRechner.rechne(zielDatum, DatumZeitRechner.stringToTime(zeitEingabe)));


                LocalDateTime temp = DatumZeitRechner.DatumZeitToLDT(zielDatum, DatumZeitRechner.stringToTime(zeitEingabe));

                if (temp.isBefore(LocalDateTime.now())) {
                    stop();

                    countdown.setText(" Ende ");
                }

                if (!animRun) {
                    stop();
                }
            }
        }.start();

    }


    public void onStopButtunClick(ActionEvent event) {

        animRun = false;
        zeit.setDisable(false);
        datePick.setDisable(false);

    }

    public void clearZeitEingabe(){
        zeit.setText("");

    }
}