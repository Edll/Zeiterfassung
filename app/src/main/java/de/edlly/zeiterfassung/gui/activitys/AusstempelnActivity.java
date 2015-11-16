package de.edlly.zeiterfassung.gui.activitys;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import de.edlly.zeiterfassung.R;
import de.edlly.zeiterfassung.controller.StempelService;
import de.edlly.zeiterfassung.model.stempeln.StempelException;
import de.edlly.zeiterfassung.model.stempeln.StempelListe;

public class AusstempelnActivity extends Activity {


    public void weiterleiten() {

        Intent intent = new Intent(this, EinstempelnActivity.class);
        startActivity(intent);
        finish();
    }

    public void stempelExceptionAnzeige(StempelException e){
        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    public class RunnableAusStempel implements Runnable {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausstempeln);
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
