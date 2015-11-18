package de.bsg.zeiterfassung.model.stempeln;

import java.util.List;

public interface IStempelOrt {

    /**
     * Diese Hard Coded Grundregeln bilden die Grundlage des Stempel Systems alle anderen Status, Orte und Regeln
     * beziehen sich auf diese Grund Stempel Attribute.
     *
     */
    //@formatter:off
    enum StempelArt {
        DEFAULT(0, ""),
        KOMMEN(1, "Kommen"), 
        GEHEN(2, "Gehen"), 
        KOMMEN_PAUSE(3, "Pause Kommen"), 
        GEHEN_PAUSE(4, "Pause Gehen"), 
        KOMMEN_ZWISCHENGANG(5, "Zwischengang Kommen"), 
        GEHEN_ZWISCHENGANG(6, "Zwischengang Gehen");
        //@formatter:on

        private final int ordnung;
        private final String status;

        StempelArt(int ordnung, String status) {
            this.ordnung = ordnung;
            this.status = status;
        }

        public int ordnung() {
            return ordnung;
        }

        public String status() {
            return status;
        }

    }

    /**
     * Id des Status der Aufgerufen wird
     * 
     * @return Id des Status
     */

    int getId();

    /**
     * Id des Status der Gesetz werden soll
     * 
     * @param id
     *            Nr des Status innerhalb des Systems
     */

    void setId(int id);

    /**
     * Anzeige Name des Status
     * 
     * @return gibt den Namen zurück.
     */

    String getName();

    /**
     * Anzeige Name des Status
     * 
     * @param name
     *            des Status
     */

    void setName(String name);

    List<IStempelOrt> getOrtList();

    void setOrtList(List<IStempelOrt> statusList);

    void createOrtList();

}