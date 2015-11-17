package de.bsg.zeiterfassung.model.stempeln;

import de.bsg.zeiterfassung.model.stempeln.IStempelOrt.StempelArt;
import de.bsg.zeiterfassung.model.zeit.AktuelleZeit;
import de.bsg.zeiterfassung.model.zeit.IZeit;

public class Stempel implements Comparable<Stempel> {
    private IZeit zeit;
    private StempelArt status;
    private IStempelOrt ort;

    public IZeit getZeit() {
        return zeit;
    }

    public Stempel() {
    }

    public Stempel(StempelArt status, IStempelOrt ort) {
        setOrt(ort);
        setStatus(status);

        IZeit aktuelleZeit = new AktuelleZeit();
        setZeit(aktuelleZeit);
    }

    public void setZeit(IZeit zeit) {
        this.zeit = zeit;
    }

    public StempelArt getStatus() {
        return status;
    }

    public void setStatus(StempelArt status) {
        this.status = status;
    }

    public void set(IZeit zeit, StempelArt status, IStempelOrt iStempelOrt) {

        setOrt(iStempelOrt);
        setZeit(zeit);
        setStatus(status);

    }

    public IStempelOrt getOrt() {
        return ort;
    }

    public void setOrt(IStempelOrt ort) {
        this.ort = ort;
    }

    @Override
    public int compareTo(Stempel stempel) {

        if ((Long) this.zeit.get() < (Long) stempel.getZeit().get())
            return -1;
        if (this.zeit.equals(stempel.getZeit().get()))
            return 0;

        return 1;

    }

}
