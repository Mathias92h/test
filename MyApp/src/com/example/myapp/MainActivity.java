package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
public final static String EXTRA_MESSAGE = "com.example.myapp.MESSAGE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Just for commit
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.main, menu);
		return true;*/
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_activity_actions, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_search:
	          //  openSearch();
	            return true;
	        case R.id.action_settings:
	         //   openSettings();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void sendMessage(View view) {
	    Intent intent = new Intent(this, DisplayMessageActivity.class);
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
}
