package de.bsg.zeiterfassung.model.zeit;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AktuelleZeit extends Zeit {
    private Calendar aktuelleZeit = new GregorianCalendar();

    @Override
    public long get() {
        return aktuelleZeit.getTime().getTime();
    }


}
