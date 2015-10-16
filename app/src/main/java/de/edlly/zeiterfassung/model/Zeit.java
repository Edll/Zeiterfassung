package de.edlly.zeiterfassung.model;

import java.text.SimpleDateFormat;

/**
 * Created by Edlly on 16.10.2015.
 */
public class Zeit implements IZeit {
    long zeit;

    @Override
    public long get() {
        return zeit;
    }

    @Override
    public void set(long zeit) {
        this.zeit = zeit;
    }

    @Override
    public String getDateTime() {

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return df.format(get());
    }

}