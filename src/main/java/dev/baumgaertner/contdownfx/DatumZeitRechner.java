package dev.baumgaertner.contdownfx;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * Alle benötigten Methoden zur verwendung des Countdown Timers
 *
 */
public class DatumZeitRechner {

    public static String rechne(LocalDate date, LocalTime time) {
        /**
         * Gebe die restzeit als String aus
         */
        Duration d = DatumPlusZeit(date, time);
        long days = d.toDays();
        d = d.minusDays(days);
        long hours = d.toHours();
        d = d.minusHours(hours);
        long minutes = d.toMinutes();
        d = d.minusMinutes(minutes);
        long seconds = d.getSeconds();

        return (days == 0 ? "" : days + " tage,") +
                (hours == 0 ? "" : hours + " stunden,") +
                (minutes == 0 ? "" : minutes + " minuten,") +
                (seconds == 0 ? "" : seconds + " sekunden");
    }

    public static Duration DatumPlusZeit(LocalDate date, LocalTime time) {
        LocalDateTime dif = LocalDateTime.of(date, time);
        Duration d = Duration.between(LocalDateTime.now(), dif);

        return d;

    }

    public static boolean checkZeitEingabe(String zeitEingabe) {

        /**
         * Prüfe ob sich parsen lässt ansonsten wird abgefangen und mit false rückgegeben
         * Gibt Info auf
         */
        LocalTime toCheck = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        try {
            toCheck = LocalTime.parse(zeitEingabe, dtf);
        } catch (RuntimeException e) {
            System.out.println(" Falsches Format");
            return false;
        }


        return true;
    }

    public static LocalTime stringToTime(String zeit) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

        return LocalTime.parse(zeit, dtf);
    }

    public static LocalDateTime DatumZeitToLDT(LocalDate date, LocalTime time){
        return LocalDateTime.of(date, time);
    }


}
