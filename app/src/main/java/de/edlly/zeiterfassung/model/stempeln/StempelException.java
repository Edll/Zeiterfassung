package de.edlly.zeiterfassung.model.stempeln;

public class StempelException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 4663138843139929307L;

    public StempelException() {

    }

    public StempelException(String s) {
        super(s);
    }

    public StempelException(StempelMeldungen meldung) {
        super("Fehler: " + meldung.fehlerID + " " + meldung.meldung());
    }

    public enum StempelMeldungen {
       //@formatter:off
        REGEL_FEHLER(1, "Regel Fehler: Es wurde keine passende Regel für den Stempel Vorgang gefunden"),
        REGEL_VORHER(2, "Falscher Stempelstatus Vorher"),
        REGEL_NACHHER(3, "Falscher Stempelstatus Nachher")
        ; //@formatter:on

        private final int fehlerID;
        private final String meldung;

        StempelMeldungen(int fehlerID, String meldung) {
            this.fehlerID = fehlerID;
            this.meldung = meldung;
        }

        public int fehlerID() {
            return fehlerID;
        }

        public String meldung() {
            return meldung;
        }

    }

}
