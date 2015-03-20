package com.example.realgroceryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends ActionBarActivity implements OnClickListener{
	final private String first_url = (String) "http://api.upcdatabase.org/xml/3bcbff8b90dde031418f05a0b9471107/";
	private HandleXML obj;
	private Button scanBtn;
	public String item_name;
	private TextView formatTxt, contentTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		scanBtn = (Button)findViewById(R.id.scan_button);
		formatTxt = (TextView)findViewById(R.id.scan_format);
		contentTxt = (TextView)findViewById(R.id.scan_content);
		scanBtn.setOnClickListener(this);
	}
	public void onClick(View v){
		//respond to clicks
		if(v.getId()==R.id.scan_button){
			IntentIntegrator scanIntegrator = new IntentIntegrator(this);//scan
			scanIntegrator.initiateScan();	
			}
		}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();//we have a result
			String scanFormat = scanningResult.getFormatName();
			String final_url = first_url + scanContent;
			obj = new HandleXML(final_url);
			obj.fetchXML();
			while (obj.parsingComplete)
			{
				item_name = obj.getFood();
			}
			if (item_name == " "){
				while(obj.parsingComplete){
					item_name=obj.getDescription();
				}
			}
			formatTxt.setText("FORMAT: " + scanFormat);
			contentTxt.setText("CONTENT: " + scanContent);
			Intent i = new Intent(ScanActivity.this, InputActivity.class);
			i.putExtra("Scan_Result", item_name);
			startActivity(i);
			}//retrieve scan result
		else{
		    Toast toast = Toast.makeText(getApplicationContext(), 
		        "No scan data received!", Toast.LENGTH_SHORT);
		    toast.show();
		}
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
}