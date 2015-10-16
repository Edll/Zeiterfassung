package de.edlly.zeiterfassung.gui.activitys;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import de.edlly.zeiterfassung.R;
import de.edlly.zeiterfassung.model.AktuelleZeit;
import de.edlly.zeiterfassung.model.IZeit;

public class EinstempelnActivity extends AppCompatActivity {
    private Timer updateUhr;
    private IZeit zeit = new AktuelleZeit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateUhr = new Timer();
        Uhr();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUhr.schedule(new UpdateUhr(new Handler(), this), 0, 60000);

    }

    @Override
    protected void onStop() {
        super.onStop();
        updateUhr.purge();
    }

    public void Uhr() {
        setContentView(R.layout.activity_einstempeln);
        TextView uhrzeit = new TextView(this);
        uhrzeit = (TextView) findViewById(R.id.textUhrzeit);
        uhrzeit.setText((CharSequence) zeit.getDateTime());
    }

    private class UpdateUhr extends TimerTask {
        Handler handler;
        EinstempelnActivity ref;

        public UpdateUhr(Handler handler, EinstempelnActivity ref) {
            super();
            this.handler = handler;
            this.ref = ref;
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    ref.Uhr();
                }
            });

        }
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
