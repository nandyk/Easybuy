package com.example.easybuy;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class Retailer extends Activity{
	  EditText username,pass;
	  int rslt;
	  String temp,temp1,name,password;
	  public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.retailer);
		  username=(EditText)findViewById(R.id.et1);
		  pass=(EditText)findViewById(R.id.et2);
		 Button b1=(Button)findViewById(R.id.b1);
		 Button b4=(Button)findViewById(R.id.b4);
		 Button b3=(Button)findViewById(R.id.b3);
	     Button b2=(Button)findViewById(R.id.b2);
	     b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username.setText("");
				pass.setText("");
			}
		});
	     b4.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Intent intent = new Intent( getApplicationContext(),MainActivity.class);
					startActivity(intent);	
				}
			});
	         
	    
	     b3.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(),"Clicked ",Toast.LENGTH_LONG).show();
					Intent intent = new Intent( getApplicationContext(),Forgot.class);
					startActivity(intent);	
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
		// Toast.makeText(getApplicationContext(),"Clicked ",Toast.LENGTH_LONG).show();
         if(checkNetwork()) {
         name = username.getText().toString().trim();
         password = pass.getText().toString(); 
                  
         AsyncHttpClient client = new AsyncHttpClient();
         RequestParams params = new RequestParams();
         
         if(!(name.trim().equals(""))){
           if(!(password.trim().equals(""))){
               //  prgDialog.show();
              params.put("name",name);
               params.put("password",password);
              
               client.post("http://192.168.1.100/android_sign.php",params ,new AsyncHttpResponseHandler() {
               	
               @Override
                   public void onSuccess(String response) {
               	   Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
                       try {
                    	   JSONArray arr = new JSONArray(response);
                    	   JSONObject obj = (JSONObject)arr.get(0);
                           System.out.println(obj.get("result"));
                           rslt = Integer.parseInt(obj.get("result").toString());
                            if(rslt == 1){
                               Toast.makeText(getApplicationContext(),"  Success login",Toast.LENGTH_LONG).show();
                               Intent i = new Intent(Retailer.this, Content.class);
                               i.putExtra("name", name);
                               startActivity(i); 
                             }else if (rslt == 0){
                                 Toast.makeText(getApplicationContext(),"  Not a Registered user ", Toast.LENGTH_LONG).show();
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
                           Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
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
  

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// 	Inflate the menu; this adds items to the action bar if it is present.
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }

}            
               
           
         



	
	
		
	

