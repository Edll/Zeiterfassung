package de.edlly.zeiterfassung.controller;

import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;

import de.edlly.zeiterfassung.model.zeit.AktuelleZeit;
import de.edlly.zeiterfassung.model.IUpdateTask;
import de.edlly.zeiterfassung.model.zeit.IZeit;
import de.edlly.zeiterfassung.model.IntervallUpdateTask;

public class Uhr {
    private Timer updateUhr;
    private IUpdateTask ref;
    private IntervallUpdateTask task;
    private Handler handler = new Handler();
    private IZeit zeit;

    public static final long UHR_UPDATE_INTERVALL = 30000L;

    public Uhr(IUpdateTask ref) {
        this.ref = ref;
        zeit = new AktuelleZeit();
    }

    public void startUhr(long updateIntervall) {
        updateUhr = new Timer();
        task = new IntervallUpdateTask(handler, ref);
        updateUhr.schedule(task, 0, updateIntervall);

    }

    public void startUhr() {
        startUhr(UHR_UPDATE_INTERVALL);
    }

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void stopUhr() {
        task.cancel();
        updateUhr.purge();
        updateUhr.cancel();
    }

    public void uhrGet(TextView view){
        zeit = new AktuelleZeit();
        view.setText((CharSequence) zeit.getDateTime());
    }
}
