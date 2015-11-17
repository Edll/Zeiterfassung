package de.bsg.zeiterfassung.test.model.zeit;

import junit.framework.TestCase;

import de.bsg.zeiterfassung.model.zeit.AktuelleZeit;
import de.bsg.zeiterfassung.model.zeit.IZeit;

public class AktuelleZeitTest extends TestCase {
    private IZeit zeit;

    @Override
    public void setUp() {
        zeit = new AktuelleZeit();
    }

    public void testGetZeit() {
        long actual = zeit.get();
        assertNotSame(0L, actual);
    }

    public void testGetDateTime() {
        String dateTime = zeit.getDateTime();
        assertTrue("Das Format stimmt nicht: " + dateTime, TesterUtil.regExDatumString(dateTime));
    }

}