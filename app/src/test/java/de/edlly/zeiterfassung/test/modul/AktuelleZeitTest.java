package de.edlly.zeiterfassung.test.modul;

import junit.framework.TestCase;

import de.edlly.zeiterfassung.model.*;

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
        assertTrue("Das Format stimmt nicht: " + dateTime, TestUtil.regExDatumString(dateTime));
    }

}