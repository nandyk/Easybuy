package com.example.easybuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class Car extends Activity{
	 TextView t1,t2,t3,t4,t5;
	 EditText e1;
	 int total,r;
	 int val,amount;
	 String name;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
	   setContentView(R.layout.car);
	   Intent mIntent = getIntent();
	   amount = mIntent.getIntExtra("amount",0);
       name = mIntent.getExtras().getString("name");
       
	   t1 = (TextView)findViewById(R.id.tv1);
	   t3 = (TextView)findViewById(R.id.tv3);
	   t4 = (TextView)findViewById(R.id.tv4);
	   t5 = (TextView)findViewById(R.id.tv5);
	 	 
	   Button b1=(Button)findViewById(R.id.b1);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 e1=(EditText)findViewById(R.id.et1);
				 val = Integer.parseInt(e1.getText().toString() );
				 total=val*50;
				 t5.setText(Integer.toString(total));
				 amount +=total;
			}
		});
		
		 
		 Button b2=(Button)findViewById(R.id.b2);
		 b2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent myIntent = new Intent(Car.this, Veg1.class);
					Toast.makeText(getApplicationContext(),"Ur total amount is "+amount, Toast.LENGTH_LONG).show();
					myIntent.putExtra("amount",amount);
					myIntent.putExtra("name", name);
					startActivity(myIntent);
				}	
		 });
		 Button b3=(Button)findViewById(R.id.b3);
		 b3.setOnClickListener(new View.OnClickListener() {
			
			  @Override
			  public void onClick(View v) {
				// TODO Auto-generated method stub
			      Intent i = new Intent( getApplicationContext(),Veg1.class);
			      startActivity(i);
			     }
		   });
		
	 }
	}
  