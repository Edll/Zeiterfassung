package de.bsg.zeiterfassung.model.stempeln;

import de.bsg.zeiterfassung.model.stempeln.IStempelOrt.StempelArt;

public interface IStempelRegel {

    /**
     * Gibt die enum Regeln für vor dem Objekt.
     */

    StempelArt[] getBefore();

    /**
     * Setz die enum Regeln für vor dem Objekt.
     */

    void setBefore(StempelArt[] before);

    /**
     * Gibt die enum Regeln für nach dem Objekt.
     */

    StempelArt[] getAfter();

    /**
     * Setz die enum Regeln für nach dem Objekt.
     */

    void setAfter(StempelArt[] after);

    /**
     * Gibt die Regel für die gewählte Stempel Art zurück.
     * 
     * @return Regel für die Angefragte Stempel Art
     * @throws StempelException
     */
    IStempelRegel getRegel(StempelArt stempelArt) throws StempelException;

    /**
     * Prüft die Regeln davor
     * 
     * @return
     * 
     * @throws StempelException
     */
    boolean checkBefore(StempelArt stempelArt, StempelArt stempelArtBefor) throws StempelException;

    /**
     * Prüft die Regeln danach
     * 
     * @throws StempelException
     */

    boolean checkAfter(StempelArt stempelArt, StempelArt stempelArtAfter) throws StempelException;

    /**
     * Erstes Element das gestempelt wird
     * 
     * @param status
     * @return
     */

    boolean checkFirst(StempelArt status);

}
