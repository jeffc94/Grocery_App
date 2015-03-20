package com.example.realgroceryapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btScan = (Button) findViewById(R.id.Scanbutton);
        Button btInput = (Button) findViewById(R.id.InputButton);
        btInput.setFocusable(true);
        btInput.setFocusableInTouchMode(true);
        btInput.requestFocus();
        btScan.setFocusable(true);
        btScan.setFocusableInTouchMode(true);
        btScan.requestFocus();
        TextView tv = (TextView) findViewById(R.id.tvName);
        Typeface face = Typeface.createFromAsset(getAssets(),
                    "airstream.TTF");
        tv.setTypeface(face);
        btScan.setTypeface(face);
        btInput.setTypeface(face);
        btScan.setOnClickListener(new View.OnClickListener() {
        		
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,ScanActivity.class);
				MainActivity.this.startActivity(intent);
			}
        });
        btInput.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,InputActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
 
    @Override
    public void onStop() {
    	super.onStop();
        clear_pref();
    }
    public void clear_pref(){
                SharedPreferences sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(this);
                Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putString("default", " ");
                editor.commit();        
    }
}
