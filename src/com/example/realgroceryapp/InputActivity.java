package com.example.realgroceryapp;


import java.util.ArrayList;
import java.util.List;

import android.app.LauncherActivity.ListItem;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class InputActivity extends ActionBarActivity implements OnItemClickListener {
	public final static String message = "com.example.realgroceryapp.MESSAGE";
	public static final String PREFS_NAME = "MyPrefsFile";//used to save
	private Button bt;
	private EditText et;
	private ListView lv;
	
	public static ArrayList<String> strArr;
	public static ArrayAdapter<String> adapter;

	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		 strArr.remove(position);
		 adapter.notifyDataSetChanged();
		 adapter = new ArrayAdapter<String>(getApplicationContext(),
					android.R.layout.simple_list_item_1, strArr);
			lv.setAdapter(adapter);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_input);
		lv = (ListView) findViewById(R.id.listView);
		lv.setFocusable(true);
	    lv.setFocusableInTouchMode(true);///add this line
	    lv.requestFocus();
	    lv.setOnItemClickListener(this);
		et = (EditText) findViewById(R.id.etItem);
		bt = (Button) findViewById(R.id.btEnter);
		strArr = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1, strArr);
		lv.setAdapter(adapter);
		
		String newString;
		if (savedInstanceState == null) {
		    Bundle extras = getIntent().getExtras();
		    if(extras == null) {
		        newString= null;
		    } else {
		        newString= extras.getString("Scan_Result");
		        strArr.add(newString);
		    }
		} else {
		    newString = (String) savedInstanceState.getSerializable("Scan_Result");
		    strArr.add(newString);
		}
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				strArr.add(et.getText().toString());
				adapter.notifyDataSetChanged();
			}
		});
	}
	
	
	public void onSearchClick(View v){
		Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
		String listString = "";

		for (String s : strArr)
		{
		    listString += ", " + s;//converts strArr to a concatenated string to allow search query
		}
		intent.putExtra(SearchManager.QUERY, "recipes containing" + listString);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.input, menu);
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
