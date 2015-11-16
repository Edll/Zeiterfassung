package de.edlly.zeiterfassung.controller;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import de.edlly.zeiterfassung.gui.activitys.EinstempelnActivity;
import de.edlly.zeiterfassung.model.stempeln.Stempel;
import de.edlly.zeiterfassung.model.stempeln.StempelException;
import de.edlly.zeiterfassung.model.stempeln.StempelListe;

/**
 * Created by edlly on 14.11.15.
 */
public class StempelService extends Service {

    // Objekt das im Service Verwaltet werden soll
    private StempelListe stempelListe = new StempelListe();

    // Binder für den Service
    private IBinder stempelBinder = new StempelServiceBinder();

    // Handler des services
    private Handler stempelHandler;

    // Runnable
    private EinstempelnActivity.RunnableStempel runnable;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stempelBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        stempelHandler = null;
        return super.onUnbind(intent);
    }

    public class StempelServiceBinder extends Binder {

        public StempelListe getStempelListe() {
            return stempelListe;
        }

        public void setstempelListe(Stempel stempel) {
            try {
                stempelListe.stempeln(stempel);

            } catch (StempelException e) {
                e.printStackTrace();
            }

        }

        public void setCallbackHandler(final Handler callback) {
            stempelHandler = callback;
        }

        public void setRunnable(final EinstempelnActivity.RunnableStempel runnableLocal) {
            runnable = runnableLocal;
        }

        public void stempeln(final Stempel stempel) {

            new Thread() {
                public void run() {

                    try {
                        stempelListe.stempeln(stempel);

                    } catch (StempelException e) {
                        runnable.stempelError(e);
                    }

                    runnable.setStempelGet(stempel);
                    stempelHandler.post(runnable);
                }
            }.start();
        }


    }
}
