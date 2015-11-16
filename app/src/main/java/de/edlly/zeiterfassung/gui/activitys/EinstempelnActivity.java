package de.edlly.zeiterfassung.gui.activitys;


import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.edlly.zeiterfassung.R;
import de.edlly.zeiterfassung.controller.StempelService;
import de.edlly.zeiterfassung.model.IUpdateTask;
import de.edlly.zeiterfassung.controller.Uhr;
import de.edlly.zeiterfassung.model.stempeln.IStempelOrt;
import de.edlly.zeiterfassung.model.stempeln.Stempel;
import de.edlly.zeiterfassung.model.stempeln.StempelException;
import de.edlly.zeiterfassung.model.stempeln.StempelListe;
import de.edlly.zeiterfassung.model.stempeln.StempelOrt;
import de.edlly.zeiterfassung.model.zeit.AktuelleZeit;

public class EinstempelnActivity extends Activity implements IUpdateTask {
    private Uhr uhr;
    private static Handler stempelCallbackHandler = new Handler();
    private StempelService.StempelServiceBinder stempelBinder;

    private ServiceConnection stempelService = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            stempelBinder = (StempelService.StempelServiceBinder) service;
            stempelBinder.setCallbackHandler(stempelCallbackHandler);
            stempelBinder.setRunnable(new RunnableStempel());

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        uhr = new Uhr(this);
        Uhr();

        final Intent stempelIntent = new Intent(this, StempelService.class);

        bindService(stempelIntent, stempelService, BIND_AUTO_CREATE);

    }


    @Override
    protected void onStart() {
        super.onStart();
        uhr.startUhr();
    }

    public void einstempeln(View view) {
        Stempel stempel = new Stempel();
        stempel.set(new AktuelleZeit(), IStempelOrt.StempelArt.KOMMEN, new StempelOrt());

        stempelBinder.stempeln(stempel);

        Toast.makeText(this, this.getString(R.string.einstempeln), Toast.LENGTH_SHORT).show();
    }

    public void weiterleiten() {

        Intent intent = new Intent(this, AusstempelnActivity.class);
        startActivity(intent);
        finish();
    }

    public void stempelExceptionAnzeige(StempelException e){
        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    public class RunnableStempel implements Runnable {
        private StempelListe listeLocal;

        public void setStempelGet(StempelListe liste) {
            this.listeLocal = liste;
        }

        public void stempelError(StempelException e){
            stempelExceptionAnzeige(e);
        }

        @Override
        public void run() {
            weiterleiten();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        uhr.stopUhr();
    }

    @Override
    protected void onDestroy() {
        stempelCallbackHandler.removeCallbacksAndMessages(null);
        unbindService(stempelService);
        stopService(new Intent(this, StempelService.class));


        super.onDestroy();
    }

    @Override
    public void update() {
        Uhr();
    }

    public void Uhr() {

        setContentView(R.layout.activity_einstempeln);
        TextView uhrzeit = new TextView(this);
        uhr.uhrGet((TextView) findViewById(R.id.textUhrzeit));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stemplen, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
