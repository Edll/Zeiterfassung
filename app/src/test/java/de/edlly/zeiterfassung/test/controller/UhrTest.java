package de.edlly.zeiterfassung.test.controller;

import android.os.Handler;

import junit.framework.TestCase;


import org.junit.Test;


import de.edlly.zeiterfassung.controller.Uhr;
import de.edlly.zeiterfassung.model.IntervallUpdateTask;

import org.mockito.Mockito;


public class UhrTest extends TestCase {

    private Uhr uhr;
    private TesterUtilUhr tester;
    private IntervallUpdateTask task;

    @Override
    public void setUp() {
        tester = new TesterUtilUhr();
        uhr = new Uhr(tester);
    }

    public void testUhrStart() throws InterruptedException {
        // FIXME: Auf grund das der Handler ein Android OS part ist wird er im Unit test nicht ausgeführt! Anderen weg such!
        Handler mockHandler = Mockito.mock(Handler.class);


        tester = new TesterUtilUhr();
        tester.reset();

        uhr = new Uhr(tester);

        uhr.setHandler(mockHandler);

        uhr.startUhr(10L);

        //Thread.sleep(2000);

        //assertFalse("Uhr flase", tester.isTest());
       // assertTrue("Uhr wurde nicht getartet", tester.isTest());

    }


    public void testUhrStop() throws InterruptedException {
        Handler mockHandler = Mockito.mock(Handler.class);
        uhr.setHandler(mockHandler);

        tester = new TesterUtilUhr();
        tester.reset();

        uhr = new Uhr(tester);
        uhr.startUhr(1L);
        uhr.stopUhr();
        // FIXME: Auf grund das der Handler ein Android OS part ist wird er im Unit test nicht ausgeführt! Anderen weg such
       //  assertFalse("Uhr wurde nicht gestoppt", tester.isTest());

    }

    @Override
    public void tearDown() {
        uhr.stopUhr();
        tester.reset();
    }
}
