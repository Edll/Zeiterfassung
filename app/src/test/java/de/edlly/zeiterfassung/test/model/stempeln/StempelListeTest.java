package de.edlly.zeiterfassung.test.model.stempeln;

import org.junit.Test;

import de.edlly.zeiterfassung.model.stempeln.IStempelOrt.StempelArt;
import de.edlly.zeiterfassung.model.stempeln.*;

import junit.framework.TestCase;

public class StempelListeTest extends TestCase {
    StempelListe liste = new StempelListe();
    StempelOrt ort;

    @Override
    public void setUp() {
        ort = new StempelOrt();
        ort.createOrtList();
    }

    @Test
    public void testListeEintragen() throws StempelException {
        Stempel stempelKommen = new Stempel(StempelArt.KOMMEN, ort.getOrtList().get(0));
        Stempel stempelGehen = new Stempel(StempelArt.GEHEN, ort.getOrtList().get(0));

        liste.stempeln(stempelKommen);

        Stempel actual = liste.getStempel(0);
        assertEquals(stempelKommen, actual);

        liste.stempeln(stempelGehen);

        Stempel actual2 = liste.getStempel(1);
        assertEquals(stempelGehen, actual2);
    }

    @Test
    public void testListeEintragenRegelFehler() {
        try {
            Stempel stempelKommen = new Stempel(StempelArt.KOMMEN, ort.getOrtList().get(0));
            liste.stempeln(stempelKommen);

            Stempel actual = liste.getStempel(0);
            assertEquals(stempelKommen, actual);

            liste.stempeln(stempelKommen);
            fail("Fehler Zweimal Einstempel Geht nicht!");

        } catch (Exception actual) {
            String expected = "Fehler: " + StempelException.StempelMeldungen.REGEL_VORHER.fehlerID();
            boolean check = actual.getLocalizedMessage().contains(expected);
            assertTrue("Fehler: falsche Exception: " + actual.getLocalizedMessage(), check);
        }

    }

    @Override
    public void tearDown() {
    }

}
