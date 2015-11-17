package de.bsg.zeiterfassung.gui.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.bsg.zeiterfassung.R;
import de.bsg.zeiterfassung.controller.Uhr;
import de.bsg.zeiterfassung.model.IUpdateTask;

public class StempeluhrActivity extends Activity implements IUpdateTask, View.OnClickListener {
    private Uhr uhr;
    private boolean eingestempelt;
    private Button bttAusstempeln;
    private Button bttEinstempeln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stempeluhr);

        // Uhr Initalisieren und das erstmal Starten.
        uhr = new Uhr(this);
        uhr();

        bttAusstempeln = (Button) findViewById(R.id.buttonAusstemplen);
        bttEinstempeln = (Button) findViewById(R.id.buttonEinstempeln);

        bttEinstempeln.setOnClickListener(this);
        bttAusstempeln.setOnClickListener(this);

        checkEingestempelt();
    }

    @Override
    protected void onStart() {
        super.onStart();
        uhr.startUhr();
    }

    @Override
    protected void onStop() {
        super.onStop();
        uhr.stopUhr();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        uhr.stopUhr();
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

    /**
     * Override Funktion aus dem UpdateTask interface
     * Alle hier hinterlegten Funktion werden Zyklisch mit einem Timer aus einem anderen Thread aufgerufen.
     */
    @Override
    public void updateTask() {
        uhr();
    }

    /**
     * Uhrzeit im Context suchen und übergeben an die uhr Methode
     */
    public void uhr() {
        TextView uhrzeitDatumView = (TextView) findViewById(R.id.stempeluhr_datum_uhrzeit);
        uhr.uhrGet(uhrzeitDatumView);
    }

    public void checkEingestempelt() {
        if (!eingestempelt) {
            buttonChangeAusstempeln();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonEinstempeln:
                if (!eingestempelt) {

                    buttonChangeEinstempeln();
                    eingestempelt = true;
                    Toast.makeText(this, this.getString(R.string.txt_einstempeln), Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.buttonAusstemplen:
                if (eingestempelt) {
                    eingestempelt = false;
                    buttonChangeAusstempeln();
                    Toast.makeText(this, this.getString(R.string.txt_ausstempeln), Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    public void buttonChangeEinstempeln() {
        bttEinstempeln.setBackgroundResource(R.drawable.bsg_grayed_button);
        bttEinstempeln.setTextColor(ContextCompat.getColor(this, R.color.bsg_btt_greyed_text));

        bttAusstempeln.setBackgroundResource(R.drawable.bsg_ausstempeln_button);
        bttAusstempeln.setTextColor(ContextCompat.getColor(this, R.color.bsg_btt_ausstempeln_text));
    }

    public void buttonChangeAusstempeln() {
        bttEinstempeln.setBackgroundResource(R.drawable.bsg_einstempeln_button);
        bttEinstempeln.setTextColor(ContextCompat.getColor(this, R.color.bsg_btt_einstempeln_text));

        bttAusstempeln.setBackgroundResource(R.drawable.bsg_grayed_button);
        bttAusstempeln.setTextColor(ContextCompat.getColor(this, R.color.bsg_btt_greyed_text));
    }
}




























