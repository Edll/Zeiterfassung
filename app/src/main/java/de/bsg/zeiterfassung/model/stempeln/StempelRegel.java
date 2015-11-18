package de.bsg.zeiterfassung.model.stempeln;

import de.bsg.zeiterfassung.model.stempeln.IStempelOrt.StempelArt;

public class StempelRegel implements IStempelRegel {
    private StempelArt[] before;
    private StempelArt[] after;

    public StempelRegel() {

    }

    @Override
    public StempelArt[] getBefore() {
        return before;
    }

    @Override
    public void setBefore(StempelArt[] before) {
        this.before = before;
    }

    @Override
    public StempelArt[] getAfter() {
        return after;
    }

    @Override
    public void setAfter(StempelArt[] after) {
        this.after = after;

    }

    @Override
    public IStempelRegel getRegel(StempelArt stempelArt) throws StempelException {
        return createRegeln(stempelArt);
    }

    @Override
    public boolean checkBefore(StempelArt stempelArt, StempelArt stempelArtBefore) throws StempelException {
        IStempelRegel regelProbe = getRegel(stempelArt);

        for (StempelArt regelIst : regelProbe.getBefore()) {
            if (regelIst == stempelArtBefore) {
                return true;
            }
        }
        throw new StempelException(StempelException.StempelMeldungen.REGEL_VORHER);
    }

    @Override
    public boolean checkAfter(StempelArt stempelArt, StempelArt stempelArtAfter) throws StempelException {
        IStempelRegel regelProbe = getRegel(stempelArt);

        for (StempelArt regelIst : regelProbe.getAfter()) {
            if (regelIst == stempelArtAfter) {
                return true;
            }
        }
        throw new StempelException(StempelException.StempelMeldungen.REGEL_NACHHER);

    }

    @Override
    public boolean checkFirst(StempelArt status) {
        if (status == StempelArt.KOMMEN) {
            return true;
        }
        return false;
    }

    /**
     * Erstellen der Regeln. @TODO Regeln auslesen auf Datenbank oder File.
     * 
     * @param stempelArt
     * @throws StempelException
     */

    private IStempelRegel createRegeln(StempelArt stempelArt) throws StempelException {
        IStempelRegel regel = new StempelRegel();
        switch (stempelArt) {

        case KOMMEN:
            regel.setBefore(new StempelArt[] { StempelArt.GEHEN });
            regel.setAfter(
                    new StempelArt[] { StempelArt.GEHEN, StempelArt.GEHEN_PAUSE, StempelArt.GEHEN_ZWISCHENGANG });
            break;

        case GEHEN:
            regel.setBefore(
                    new StempelArt[] { StempelArt.KOMMEN, StempelArt.KOMMEN_PAUSE, StempelArt.KOMMEN_ZWISCHENGANG });
            regel.setAfter(new StempelArt[] { StempelArt.KOMMEN });
            break;

        case GEHEN_PAUSE:
            regel.setBefore(
                    new StempelArt[] { StempelArt.KOMMEN, StempelArt.KOMMEN_ZWISCHENGANG, StempelArt.KOMMEN_PAUSE });
            regel.setAfter(new StempelArt[] { StempelArt.KOMMEN_PAUSE });
            break;

        case KOMMEN_PAUSE:
            regel.setBefore(new StempelArt[] { StempelArt.GEHEN_PAUSE });
            regel.setAfter(
                    new StempelArt[] { StempelArt.GEHEN_PAUSE, StempelArt.GEHEN_ZWISCHENGANG, StempelArt.GEHEN });
            break;

        case GEHEN_ZWISCHENGANG:
            regel.setBefore(
                    new StempelArt[] { StempelArt.KOMMEN, StempelArt.KOMMEN_ZWISCHENGANG, StempelArt.KOMMEN_PAUSE });
            regel.setAfter(new StempelArt[] { StempelArt.KOMMEN_ZWISCHENGANG });
            break;

        case KOMMEN_ZWISCHENGANG:
            regel.setBefore(new StempelArt[] { StempelArt.GEHEN_ZWISCHENGANG });
            regel.setAfter(
                    new StempelArt[] { StempelArt.GEHEN_PAUSE, StempelArt.GEHEN_ZWISCHENGANG, StempelArt.GEHEN });
            break;

        default:
            throw new StempelException(StempelException.StempelMeldungen.REGEL_FEHLER);
        }
        return regel;
    }
}
