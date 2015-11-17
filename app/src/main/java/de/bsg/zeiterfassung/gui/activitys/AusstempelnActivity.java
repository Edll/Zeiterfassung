package de.bsg.zeiterfassung.gui.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import de.bsg.zeiterfassung.R;
import de.bsg.zeiterfassung.model.stempeln.StempelException;
import de.bsg.zeiterfassung.model.stempeln.StempelListe;
import de.bsg.zeiterfassung.model.zeit.Zeit;

public class AusstempelnActivity extends Activity {
    StempelListe stempelListe = new StempelListe();

    public void weiterleiten() {
        Intent intent = new Intent(this, EinstempelnActivity.class);
        startActivity(intent);
        finish();
    }

    public void stempelExceptionAnzeige(StempelException e){
        Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ausstempeln);

        Intent intent=this.getIntent();
        long zeit = intent.getLongExtra("zeit", 0L);

        Zeit zeitFormat = new Zeit();
        zeitFormat.set(zeit);


        TextView eingestempeltUhr = (TextView) findViewById(R.id.textEinstempelZeit);
        eingestempeltUhr.setText(zeitFormat.getDateTime());
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
