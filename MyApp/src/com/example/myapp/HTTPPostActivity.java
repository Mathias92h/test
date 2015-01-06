package com.example.myapp;
 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
 

import android.util.Log;
 
public class HTTPPostActivity {
 
	public static String fromMySQL="";
	private String username;
	private String password;
    
	public HTTPPostActivity(String username, String password){
		this.username=username;
		this.password=password;
		makePostRequest();
	}
	
	
	//http://hayageek.com/android-http-post-get/
    private void makePostRequest() {
 
         
        HttpClient httpClient = new DefaultHttpClient();
                                // replace with your url
        HttpPost httpPost = new HttpPost("http://192.168.178.62/loginPost.php"); 
 
 
        //Post Data
        List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
        nameValuePair.add(new BasicNameValuePair("username", username));
        nameValuePair.add(new BasicNameValuePair("password", password));
 
         
        //Encoding POST data
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
        } catch (UnsupportedEncodingException e) {
            // log exception
            e.printStackTrace();
        }
 
        //making POST request.
        try {
            HttpResponse response = httpClient.execute(httpPost);
            // write response to log
            fromMySQL = response.toString();
            Log.d("Http Post Response:", response.toString());
        } catch (ClientProtocolException e) {
            // Log exception
            e.printStackTrace();
        } catch (IOException e) {
            // Log exception
            e.printStackTrace();
        }
 
    }
 
}