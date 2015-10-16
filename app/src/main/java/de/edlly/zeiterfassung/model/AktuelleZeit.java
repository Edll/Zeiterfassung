package de.edlly.zeiterfassung.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AktuelleZeit extends Zeit {
    Calendar aktuelleZeit = new GregorianCalendar();

    @Override
    public long get() {
        return aktuelleZeit.getTime().getTime();
    }


}
