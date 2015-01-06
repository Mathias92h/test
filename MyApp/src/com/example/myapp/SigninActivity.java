package com.example.myapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.content.Context;
import android.os.AsyncTask;
//import android.widget.TextView;
import android.widget.ProgressBar;

public class SigninActivity extends AsyncTask<String,Void,String>{
//	private TextView phpresult;
	public static String fromMySQL="";
	public int numberOfRows=1;
	private Context context;
	private ProgressBar progBar;

	public SigninActivity(Context context, ProgressBar progBar) {
		this.context = context;
		this.progBar = progBar;
		//this.phpresult = phpresult;
	}
	
	protected void onPreExecute(){
		
	}
	
	@Override
	protected String doInBackground(String... arg0) {
	     
	    try{
	    	String username = (String)arg0[0];
	        String password = (String)arg0[1];
	        String link="http://192.168.178.62/loginPost.php";
	        String data  = URLEncoder.encode("username", "UTF-8") 
	        + "=" + URLEncoder.encode(username, "UTF-8");
	        data += "&" + URLEncoder.encode("password", "UTF-8") 
	        + "=" + URLEncoder.encode(password, "UTF-8");
	        URL url = new URL(link);
	        URLConnection conn = url.openConnection(); 
	        conn.setDoOutput(true); 
	        OutputStreamWriter wr = new OutputStreamWriter
	        (conn.getOutputStream()); 
	        wr.write( data ); 
	        wr.flush(); 
	        BufferedReader reader = new BufferedReader
	        (new InputStreamReader(conn.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        String line = null;
	        // Read Server Response
	        while((line = reader.readLine()) != null)
	        {
	           sb.append(line);
	           numberOfRows++;
	           progBar.setProgress(numberOfRows);
	           break;
	        }
	        return sb.toString();
	     }catch(Exception e){
	        return new String("Exception: " + e.getMessage());
	     }
	      
	}
	
	@Override
	protected void onPostExecute(String result){
		fromMySQL = result;
		DisplayMessageActivity.nextStep();
		//this.phpresult.setText(fromMySQL);
	}

}
