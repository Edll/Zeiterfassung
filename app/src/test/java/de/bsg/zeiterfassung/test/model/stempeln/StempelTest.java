package de.bsg.zeiterfassung.test.model.stempeln;

import de.bsg.zeiterfassung.model.stempeln.IStempelOrt.StempelArt;
import de.bsg.zeiterfassung.model.zeit.AktuelleZeit;
import de.bsg.zeiterfassung.model.zeit.IZeit;
import de.bsg.zeiterfassung.model.stempeln.*;
import junit.framework.TestCase;

public class StempelTest extends TestCase {
    private Stempel stempel;

    @Override
    public void setUp() {
        stempel = new Stempel();
    }

    public void testSetStempeln() {
        IZeit zeit = new AktuelleZeit();
        StempelArt status = StempelArt.KOMMEN;

        IStempelOrt ort = new StempelOrt();
        ort.createOrtList();

        stempel.set(zeit, status, ort.getOrtList().get(0));

        assertEquals(ort.getOrtList().get(0), stempel.getOrt());
        assertEquals(zeit, stempel.getZeit());
        assertEquals(status, stempel.getStatus());
    }

}
