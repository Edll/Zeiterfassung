package de.bsg.zeiterfassung.gui.activitys;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.bsg.zeiterfassung.R;
import de.bsg.zeiterfassung.model.IUpdateTask;
import de.bsg.zeiterfassung.controller.Uhr;
import de.bsg.zeiterfassung.model.stempeln.IStempelOrt;
import de.bsg.zeiterfassung.model.stempeln.Stempel;
import de.bsg.zeiterfassung.model.stempeln.StempelException;
import de.bsg.zeiterfassung.model.stempeln.StempelListe;
import de.bsg.zeiterfassung.model.stempeln.StempelOrt;
import de.bsg.zeiterfassung.model.zeit.AktuelleZeit;

public class EinstempelnActivity extends Activity implements IUpdateTask {
    private Uhr uhr;
    Stempel stempel;
    StempelListe liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_einstempeln);

        mitarbeiterNameAnzeigen();

        uhr = new Uhr(this);
        uhr();
    }


    @Override
    protected void onStart() {
        super.onStart();
        uhr.startUhr();
    }

    public void einstempeln(View view) {
        stempel = new Stempel();
        stempel.set(new AktuelleZeit(), IStempelOrt.StempelArt.KOMMEN, new StempelOrt());

        try {
            liste = new StempelListe();
            liste.stempeln(stempel);
        } catch (StempelException e) {
            stempelExceptionAnzeige(e);
        }

        Toast.makeText(this, this.getString(R.string.txt_einstempeln), Toast.LENGTH_SHORT).show();

        weiterleiten();
    }

    public void mitarbeiterNameAnzeigen() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String string = sharedPreferences.getString("example_text", "");

        TextView mitarbeiterName = (TextView) findViewById(R.id.textViewMitarbeiterName);
        mitarbeiterName.setText(string);
    }

    public void weiterleiten() {

        Intent intent = new Intent(this, AusstempelnActivity.class);
        intent.putExtra("zeit", stempel.getZeit().get());
        startActivity(intent);
        finish();
    }

    public void stempelExceptionAnzeige(StempelException e) {
        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
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
        super.onDestroy();
    }

    @Override
    public void updateTask() {
        uhr();
    }

    public void uhr() {
        uhr.uhrGet((TextView) findViewById(R.id.textUhrzeit));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
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
            Intent intent = new Intent(this, EinstellungenActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
