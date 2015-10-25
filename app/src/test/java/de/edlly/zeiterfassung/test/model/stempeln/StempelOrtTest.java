package de.edlly.zeiterfassung.test.model.stempeln;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import de.edlly.zeiterfassung.model.stempeln.*;

public class StempelOrtTest extends TestCase {
    private IStempelOrt ort;

    @Override
    public void setUp() {
        ort = new StempelOrt();
    }

    public void testSetGetId() {
        int expected = 1;

        ort.setId(expected);
        int actual = ort.getId();

        assertEquals(expected, actual);
    }

    public void testSetGetName() {
        String expected = "Büro";

        ort.setName(expected);
        String actual = ort.getName();

        assertEquals(expected, actual);
    }

    public void testSetGetList() {
        String expectedName = "Büro";
        int expectedId = 1;

        IStempelOrt stempelOrt = new StempelOrt();
        stempelOrt.setId(expectedId);
        stempelOrt.setName(expectedName);

        List<IStempelOrt> ortList = new ArrayList<IStempelOrt>();
        ortList.add(stempelOrt);

        ort.setOrtList(ortList);

        assertEquals(ort.getOrtList(), ortList);
    }

    public void testCreateGetList() {
        ort.createOrtList();

        assertNotNull(ort.getOrtList());
    }

}