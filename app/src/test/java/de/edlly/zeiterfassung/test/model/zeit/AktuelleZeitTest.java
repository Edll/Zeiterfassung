package de.edlly.zeiterfassung.test.model.zeit;

import junit.framework.TestCase;

import de.edlly.zeiterfassung.model.zeit.AktuelleZeit;
import de.edlly.zeiterfassung.model.zeit.IZeit;

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