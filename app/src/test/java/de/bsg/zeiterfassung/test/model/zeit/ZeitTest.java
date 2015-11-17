package de.bsg.zeiterfassung.test.model.zeit;

import junit.framework.TestCase;

import de.bsg.zeiterfassung.model.zeit.IZeit;
import de.bsg.zeiterfassung.model.zeit.Zeit;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ZeitTest extends TestCase {
    private IZeit zeit;

    @Override
    public void setUp() {
        zeit = new Zeit();
    }

    public void testSetGetZeit() {
        zeit.set(100L);
        long actual = zeit.get();
        assertNotSame(0L, actual);
    }

    public void testGetDateTime() {
        Calendar aktuelleZeit = new GregorianCalendar();
        zeit.set(aktuelleZeit.getTime().getTime());

        String dateTime = zeit.getDateTime();
        assertTrue("Das Format stimmt nicht: " + dateTime, TesterUtil.regExDatumString(dateTime));
    }

}
