package de.edlly.zeiterfassung.gui.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import de.edlly.zeiterfassung.R;
import de.edlly.zeiterfassung.model.IUpdateTask;
import de.edlly.zeiterfassung.controller.Uhr;
import de.edlly.zeiterfassung.model.AktuelleZeit;
import de.edlly.zeiterfassung.model.IZeit;

public class EinstempelnActivity extends AppCompatActivity implements IUpdateTask {
    private Uhr uhr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uhr = new Uhr(this);
        Uhr();
    }

    @Override
    protected void onStart() {
        super.onStart();
        uhr.startUhr();
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
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
