package de.bsg.zeiterfassung.test.controller;

import de.bsg.zeiterfassung.model.IUpdateTask;

/**
 * Erstellt ein Test Update für den Intervall Updater
 *
 * Created by Edlly on 17.10.2015.
 */
public class TesterUtilUhr implements IUpdateTask {
    private boolean test;

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }



    @Override
    public void updateTask() {
      test = true;
    }

    public void reset(){
        test = false;
    }
}
