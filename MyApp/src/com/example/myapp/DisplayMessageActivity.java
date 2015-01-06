package com.example.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DisplayMessageActivity extends ActionBarActivity {

	private static TextView phpresult, textViewResult;
	public static DBWords dbWords;
	private ProgressBar progBar;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
     
     // Get the message from the intent
     Intent intent = getIntent();
     String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
  
     setContentView(R.layout.activity_display_message);
     textViewResult = (TextView)findViewById(R.id.textView1);
     textViewResult.setText(message);
     phpresult = (TextView)findViewById(R.id.textViewResult);
     progBar = (ProgressBar) findViewById(R.id.progressBar1);
     progBar.setMax(2500);
     progBar.setProgress(0);
     dbWords = new DBWords(this);
     
     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        
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

    public void loginPost(View view){
        String username = "validUser";
        String password = "dG_s2a6Q-aseA8Dp_Yza";
        // Alternativ zu dem untenstehden Verfahren mit asynchroner Klasse
        
        new SigninActivity(this, progBar).execute(username,password);
        
        /*while(SigninActivity.fromMySQL==""){
        	progBar.setProgress(SigninActivity.numberOfRows);
        }*/
        //new HTTPPostActivity(username,password);

        
     }
    
    public static void nextStep(){
    	phpresult.setText("Ready for Insert");
    	dbWords.insert(SigninActivity.fromMySQL);
        
        
  	    Cursor resultSet = dbWords.getVocable(1100);
  	    resultSet.moveToFirst();
  	    phpresult.setText(resultSet.getString(1)+" "+resultSet.getString(2)); 
  	    if (!resultSet.isClosed()) {
               resultSet.close();
        }
    }
    
}
