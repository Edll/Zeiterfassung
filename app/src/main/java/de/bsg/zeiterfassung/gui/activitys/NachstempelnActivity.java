package de.bsg.zeiterfassung.gui.activitys;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;

import de.bsg.zeiterfassung.R;

public class NachstempelnActivity extends Activity {
    private TimePicker timerpicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nachstempeln);
        timerpicker = (TimePicker) findViewById(R.id.timePicker);
        timerpicker.setIs24HourView(true);
    }

    private void setupActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, EinstellungenActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_stempeluhr:
                return true;
            case R.id.action_stundenuebersicht:
                Intent intentStudenUebersicht = new Intent(this, MonatsuebersichtActivity.class);
                startActivity(intentStudenUebersicht);
                return true;
            case R.id.action_nachstempeln:
                Intent intentNachstempeln = new Intent(this, NachstempelnActivity.class);
                startActivity(intentNachstempeln);
                return true;
            case R.id.action_beenden:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
