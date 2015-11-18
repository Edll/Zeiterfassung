package de.bsg.zeiterfassung.model.zeit;

/**
 * Enthält die Grundlegeden Zeitfunktionen.
 * Created by Edlly on 16.10.2015.
 */
public interface IZeit {
    /**
     * Gibt den aktuellen Zeitwert im Objekt zurück
     *
     * @return Zeit in ms
     */
    long get();

    /**
     * Setz die Zeit im Objekt
     *
     * @param zeit als ms
     */
    void set(long zeit);

    /**
     * Gibt die im Objekt stehende Zeit als String zurück
     *
     * @return Formatet String TT.MM.JJJJ - HH:MM
     */
    String getDateTime();
}
