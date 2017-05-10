package com.example.easybuy;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShoppingCart extends Activity{
   //double sum;
   TextView t1,t2;
   int r1,r2,r3,total=0,rslt;
   int total1=0,amount;
   String name,temp,amount1;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	 setContentView(R.layout.shoppingcart);
	 Intent mIntent = getIntent();
	 amount = mIntent.getIntExtra("amount",0);
     name = mIntent.getExtras().getString("name");

	 t1 = (TextView)findViewById(R.id.tv1);
	 t2 = (TextView)findViewById(R.id.tv2);
	 t2.setText(Integer.toString(amount));
	 Button b1=(Button)findViewById(R.id.b1);
	 Button b2=(Button)findViewById(R.id.b2);
	 b2.setOnClickListener(new View.OnClickListener() {
		
		  @Override
		  public void onClick(View v) {
			// TODO Auto-generated method stub
		      Intent i = new Intent( getApplicationContext(),Content.class);
		      startActivity(i);
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
		 String amount1 = Integer.toString(amount);
	    
       if(checkNetwork()) {
       AsyncHttpClient client = new AsyncHttpClient();
       RequestParams params = new RequestParams();
         if(!(name.trim().equals(""))){
             params.put("name",name);
             params.put("amount1",amount1);
             client.post("http://192.168.1.100/deliver.php",params ,new AsyncHttpResponseHandler() {
             @Override
                 public void onSuccess(String response) {
          	      System.out.println(response);
             	      try {
             	         JSONArray arr = new JSONArray(response);
                   	     JSONObject obj = (JSONObject)arr.get(0);
                         System.out.println(obj.get("result"));
                         rslt = Integer.parseInt(obj.get("result").toString());
                         if(rslt == 1){
                             Toast.makeText(getApplicationContext(),"Ur products will be delivered within 5 days",Toast.LENGTH_LONG).show();
                             Intent i = new Intent(ShoppingCart.this,Thank.class);
                             startActivity(i); 
                         }else if (rslt == 0){
                      	     Toast.makeText(getApplicationContext(),"Problem in delivery", Toast.LENGTH_LONG).show();
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
                Toast.makeText(getApplicationContext(), "Problem in username ", Toast.LENGTH_SHORT).show();
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
