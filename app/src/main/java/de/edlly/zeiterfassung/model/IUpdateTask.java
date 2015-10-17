package de.edlly.zeiterfassung.model;

/**
 * Update klasse für die Uhr.
 * <p/>
 * Jede Klasse die eine Uhr mit Updater benutzen will muss diese Interface haben um seinen eigenen Status an die Update Klasse übergeben zu können.
 * Die Methode update beinhaltet dabei den Aufruf der Uhr.
 * <p/>
 * Created by Edlly on 17.10.2015.
 */
public interface IUpdateTask {
    /**
     * Diese methode wird von dem Update Timer aufgerufen in der Methode die den Timer einbindet.
     */
    public void update();

}
