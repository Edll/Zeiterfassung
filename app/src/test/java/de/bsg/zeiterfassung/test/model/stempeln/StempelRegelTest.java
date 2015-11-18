package de.bsg.zeiterfassung.test.model.stempeln;

import org.junit.Test;

import de.bsg.zeiterfassung.model.stempeln.IStempelOrt.StempelArt;
import de.bsg.zeiterfassung.model.stempeln.StempelException.StempelMeldungen;
import de.bsg.zeiterfassung.model.stempeln.*;
import junit.framework.TestCase;

public class StempelRegelTest extends TestCase {
    private IStempelRegel regel;

    @Override
    public void setUp() {
        regel = new StempelRegel();
    }

    @Test
    public void testSetGetBefore() {
        StempelArt[] before = new StempelArt[] { StempelArt.GEHEN, StempelArt.GEHEN_ZWISCHENGANG };

        regel.setBefore(before);
        int expected = 2;
        int actual = regel.getBefore().length;
        assertEquals(expected, actual);

        assertNotNull(regel.getBefore()[0]);

        assertEquals(StempelArt.GEHEN, regel.getBefore()[0]);
        assertEquals(StempelArt.GEHEN_ZWISCHENGANG, regel.getBefore()[1]);
    }

    @Test
    public void testSetGetAfter() {
        StempelArt[] after = new StempelArt[] { StempelArt.KOMMEN, StempelArt.KOMMEN_ZWISCHENGANG };

        regel.setAfter(after);
        int expected = 2;
        int actual = regel.getAfter().length;
        assertEquals(expected, actual);

        assertNotNull(regel.getAfter()[0]);

        assertEquals(StempelArt.KOMMEN, regel.getAfter()[0]);
        assertEquals(StempelArt.KOMMEN_ZWISCHENGANG, regel.getAfter()[1]);
    }

    @Test
    public void testGetRegelException() {
        try {

            regel.getRegel(StempelArt.DEFAULT);
            fail("Exception wurde erwartet da default keine Regeln hat.");

        } catch (Exception actual) {

            String expected = "Fehler: " + StempelMeldungen.REGEL_FEHLER.fehlerID();
            boolean check = actual.getLocalizedMessage().contains(expected);
            assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
        }

    }

    @Test
    public void testGetRegel() throws StempelException {
        int expected = 1;
        IStempelRegel actual = regel.getRegel(StempelArt.KOMMEN);
        assertEquals(expected, actual.getBefore().length);

        expected = 3;
        assertEquals(expected, actual.getAfter().length);
    }

    @Test
    public void testRegelCheckBeforeException() {
        try {
            StempelArt actual = StempelArt.KOMMEN;

            regel.checkBefore(actual, StempelArt.KOMMEN);
            fail("Fehler auf Kommen darf laut Regel nicht kommen Folgen.");
        } catch (Exception actual) {

            String expected = "Fehler: " + StempelMeldungen.REGEL_VORHER.fehlerID();
            boolean check = actual.getLocalizedMessage().contains(expected);
            assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
        }
    }

    @Test
    public void testRegelCheckAfterException() {
        try {
            StempelArt actual = StempelArt.GEHEN;

            regel.checkAfter(actual, StempelArt.GEHEN);
            fail("Fehler auf Kommen darf laut Regel nicht kommen Folgen.");
        } catch (Exception actual) {

            String expected = "Fehler: " + StempelMeldungen.REGEL_NACHHER.fehlerID();
            boolean check = actual.getLocalizedMessage().contains(expected);
            assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
        }
    }

    @Test
    public void testRegelCheckAfter() throws StempelException {
        StempelArt actual = StempelArt.KOMMEN;

        assertTrue(regel.checkAfter(actual, StempelArt.GEHEN));
        assertTrue(regel.checkAfter(actual, StempelArt.GEHEN_PAUSE));
        assertTrue(regel.checkAfter(actual, StempelArt.GEHEN_ZWISCHENGANG));

        actual = StempelArt.GEHEN;
        assertTrue(regel.checkAfter(actual, StempelArt.KOMMEN));

        actual = StempelArt.GEHEN_PAUSE;
        assertTrue(regel.checkAfter(actual, StempelArt.KOMMEN_PAUSE));

        actual = StempelArt.KOMMEN_PAUSE;
        assertTrue(regel.checkAfter(actual, StempelArt.GEHEN));
        assertTrue(regel.checkAfter(actual, StempelArt.GEHEN_ZWISCHENGANG));

        actual = StempelArt.KOMMEN_ZWISCHENGANG;
        assertTrue(regel.checkAfter(actual, StempelArt.GEHEN));
        assertTrue(regel.checkAfter(actual, StempelArt.GEHEN_PAUSE));

        actual = StempelArt.GEHEN_ZWISCHENGANG;
        assertTrue(regel.checkAfter(actual, StempelArt.KOMMEN_ZWISCHENGANG));
    }

    @Test
    public void testRegelCheckBefore() throws StempelException {
        StempelArt actual = StempelArt.KOMMEN;

        assertTrue(regel.checkBefore(actual, StempelArt.GEHEN));

        actual = StempelArt.GEHEN;
        assertTrue(regel.checkBefore(actual, StempelArt.KOMMEN));
        assertTrue(regel.checkBefore(actual, StempelArt.KOMMEN_PAUSE));
        assertTrue(regel.checkBefore(actual, StempelArt.KOMMEN_ZWISCHENGANG));

        actual = StempelArt.GEHEN_PAUSE;
        assertTrue(regel.checkBefore(actual, StempelArt.KOMMEN_PAUSE));
        assertTrue(regel.checkBefore(actual, StempelArt.KOMMEN_ZWISCHENGANG));
        assertTrue(regel.checkBefore(actual, StempelArt.KOMMEN));

        actual = StempelArt.KOMMEN_PAUSE;
        assertTrue(regel.checkBefore(actual, StempelArt.GEHEN_PAUSE));

        actual = StempelArt.KOMMEN_ZWISCHENGANG;
        assertTrue(regel.checkBefore(actual, StempelArt.GEHEN_ZWISCHENGANG));

        actual = StempelArt.GEHEN_ZWISCHENGANG;
        assertTrue(regel.checkBefore(actual, StempelArt.KOMMEN_ZWISCHENGANG));
        assertTrue(regel.checkBefore(actual, StempelArt.KOMMEN_PAUSE));
        assertTrue(regel.checkBefore(actual, StempelArt.KOMMEN));
    }

    @Test
    public void testRegelCheckFirst() throws StempelException {
        StempelArt actual = StempelArt.KOMMEN;
        assertTrue(regel.checkFirst(actual));

        StempelArt actual2 = StempelArt.GEHEN;
        assertFalse(regel.checkFirst(actual2));
    }

    @Override
    public void tearDown() {
    }

}
