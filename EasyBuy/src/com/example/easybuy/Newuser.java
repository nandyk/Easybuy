package com.example.easybuy;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import android.telephony.SmsManager;
import android.support.v4.app.ActivityCompat;

public class Newuser extends Activity{
	
	EditText e1,e2,e3,e4,e5;
	String name,mobile,email,city,password,message;
	int rslt;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newuser);
		Button b1=(Button)findViewById(R.id.b1);
		
		Button b2=(Button)findViewById(R.id.b2);
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 e1=(EditText)findViewById(R.id.et1);
				 e2=(EditText)findViewById(R.id.et2);
				 e3=(EditText)findViewById(R.id.et3);
				 e4=(EditText)findViewById(R.id.et4);
				 e5=(EditText)findViewById(R.id.et5);
				 e1.setText("");
				 e2.setText("");
				 e3.setText("");
				 e4.setText("");
				 e5.setText("");
				 Toast.makeText(getApplicationContext(),"Clicked ",Toast.LENGTH_LONG).show();
			}
		});
	}
	 private boolean checkNetwork(){
         ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
         if(connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected()){
           return true;
          }else {
           return false;
          }
     }
	 public void login(View view) {
		 Toast.makeText(getApplicationContext(),"Clicked ",Toast.LENGTH_LONG).show();
         if(checkNetwork()) {
        	   name = e1.getText().toString().trim();
               mobile = e2.getText().toString(); 
               email = e3.getText().toString(); 
               city = e4.getText().toString(); 
               password = e5.getText().toString(); 
              
              AsyncHttpClient client = new AsyncHttpClient();
              RequestParams params = new RequestParams();
              
              if(!(name.trim().equals(""))){
                  if(!(password.trim().equals(""))){
                      params.put("name",name);
                      params.put("mobile",mobile);
                      params.put("email",email);
                      params.put("city",city);
                      params.put("password",password);
                      
                      client.post("http://192.168.1.100/register.php",params ,new AsyncHttpResponseHandler() {
            	  
                      	
                          @Override
                              public void onSuccess(String response) {
                        	      System.out.println(response);
       	                          try {
                                	  JSONArray arr = new JSONArray(response);
       	                       	      JSONObject obj = (JSONObject)arr.get(0);
       	                              System.out.println(obj.get("result"));
       	                              rslt = Integer.parseInt(obj.get("result").toString());
                                      if(rslt == 1){
	                                      Toast.makeText(getApplicationContext()," Ur details have added successfully",Toast.LENGTH_LONG).show();
	                                      sendSMSMessage();
                                       }else if (rslt == 0){
       	                                  Toast.makeText(getApplicationContext()," Registered failure!! ", Toast.LENGTH_LONG).show();
                                       }
                                     }catch (Exception e) {
                                         // TODO Auto-generated catch block
                                  	      Toast.makeText(getApplicationContext(), e+" ", Toast.LENGTH_LONG).show();
                                         Toast.makeText(getApplicationContext()," Error Occured [Server's JSON response might be invalid]! ", Toast.LENGTH_LONG).show();
                                         e.printStackTrace();
                                      }
                              }
                             @Override
                             public void onFailure(int statusCode, Throwable error,String content) {
                                   // TODO Auto-generated method stub
                           	      if(statusCode == 404){
                                      Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                                  }else if(statusCode == 500){
                                      Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                                  }else{
                                      Toast.makeText(getApplicationContext(),statusCode+ "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
                              }
                            }
                          });
                        }else{
                             Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_SHORT).show();
                        }
                     }else{
                          Toast.makeText(getApplicationContext(), "Please enter Username", Toast.LENGTH_SHORT).show();
                   }
                 }
                 else {
                    Toast.makeText(getApplicationContext(), "No network available", Toast.LENGTH_SHORT).show();
                 }
               }


              @Override
              public void onBackPressed() {
                finish();
           	   Intent intent = new Intent(Intent.ACTION_MAIN);
           	   intent.addCategory(Intent.CATEGORY_HOME);
           	   startActivity(intent);
              }
              
             protected void sendSMSMessage() {
        	    Log.i("mobile",e1.getText().toString());
        	      message ="Welcome To Bharathi Store "+name+"Hearty Thanks For Registering and Your username is "+name+" and your password is"+password+" ";
        	      try {
        	         SmsManager smsManager = SmsManager.getDefault();
        	         smsManager.sendTextMessage(mobile, null, message, null, null);
        	         Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        	      } 
        	      
        	      catch (Exception e) {
        	         Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
        	         e.printStackTrace();
        	      }
        	   }
              @Override
              public boolean onCreateOptionsMenu(Menu menu) {
              	// 	Inflate the menu; this adds items to the action bar if it is present.
              	getMenuInflater().inflate(R.menu.main, menu);
              	return true;
              }
                      
         }
