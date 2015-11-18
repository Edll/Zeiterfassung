package de.bsg.zeiterfassung.model;

/**
 * Update klasse für die uhr.
 * <p/>
 * Jede Klasse die eine uhr mit Updater benutzen will muss diese Interface haben um seinen eigenen Status an die Update Klasse übergeben zu können.
 * Die Methode updateTask beinhaltet dabei den Aufruf der uhr.
 * <p/>
 * Created by Vervoorst on 17.10.2015.
 */
public interface IUpdateTask {
    /**
     * Diese methode wird von dem Update Timer aufgerufen in der Methode die den Timer einbindet.
     */
    void updateTask();

}
