package de.bsg.zeiterfassung.model;

import android.os.Handler;

import java.util.TimerTask;

/**
 * Stellt eine Update Task für die Uhr bereit. Ruft die Methode Update aus dem Interface IUpdateTask auf.
 * <p/>
 * Created by Edlly on 17.10.2015.
 */
public class IntervallUpdateTask extends TimerTask {
    Handler handler;
    IUpdateTask ref;

    public IntervallUpdateTask(Handler handler, IUpdateTask ref) {
        super();
        this.handler = handler;
        this.ref = ref;
    }

    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {

                ref.update();
            }
        });

    }


}
