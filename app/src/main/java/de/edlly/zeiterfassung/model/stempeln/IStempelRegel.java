package de.edlly.zeiterfassung.model.stempeln;

import de.edlly.zeiterfassung.model.stempeln.IStempelOrt.StempelArt;

public interface IStempelRegel {

    /**
     * Gibt die enum Regeln für vor dem Objekt.
     */

    public StempelArt[] getBefore();

    /**
     * Setz die enum Regeln für vor dem Objekt.
     */

    public void setBefore(StempelArt[] before);

    /**
     * Gibt die enum Regeln für nach dem Objekt.
     */

    public StempelArt[] getAfter();

    /**
     * Setz die enum Regeln für nach dem Objekt.
     */

    public void setAfter(StempelArt[] after);

    /**
     * Gibt die Regel für die gewählte Stempel Art zurück.
     * 
     * @return Regel für die Angefragte Stempel Art
     * @throws StempelException
     */
    public IStempelRegel getRegel(StempelArt stempelArt) throws StempelException;

    /**
     * Prüft die Regeln davor
     * 
     * @return
     * 
     * @throws StempelException
     */
    public boolean checkBefore(StempelArt stempelArt, StempelArt stempelArtBefor) throws StempelException;

    /**
     * Prüft die Regeln danach
     * 
     * @throws StempelException
     */

    public boolean checkAfter(StempelArt stempelArt, StempelArt stempelArtAfter) throws StempelException;

    /**
     * Erstes Element das gestempelt wird
     * 
     * @param status
     * @return
     */

    public boolean checkFirst(StempelArt status);

}
