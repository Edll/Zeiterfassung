package de.edlly.zeiterfassung.gui.activitys;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import de.edlly.zeiterfassung.R;
import de.edlly.zeiterfassung.model.IUpdateTask;
import de.edlly.zeiterfassung.controller.Uhr;

public class EinstempelnActivity extends Activity implements IUpdateTask {
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

    public void einstempeln() {
        Toast.makeText(this, "Eingestempelt", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, AusstempelnActivity.class);
        startActivity(intent);
        finish();
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
        getMenuInflater().inflate(R.menu.menu_stemplen, menu);

        Button buttonEinstempeln = (Button) findViewById(R.id.buttonEinstempeln);
        buttonEinstempeln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                einstempeln();
            }
        });

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
