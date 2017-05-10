package com.example.easybuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Content extends Activity{
	int amount;
	String name;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content);
		Intent mIntent = getIntent();
		amount = mIntent.getIntExtra("amount",0);
        name = mIntent.getExtras().getString("name");
       
		Button b1=(Button)findViewById(R.id.b1);
		Button b2=(Button)findViewById(R.id.b2);
		Button b3=(Button)findViewById(R.id.b3);
		Button b4=(Button)findViewById(R.id.b4);
		Button b5=(Button)findViewById(R.id.b5);
		Button b6=(Button)findViewById(R.id.b6);
		Button b7=(Button)findViewById(R.id.b7);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
		    @Override
			public void onClick(View v) {		
				Intent intent = new Intent(Content.this,Vegetable.class);
				intent.putExtra("amount",amount);
				intent.putExtra("name", name);
				startActivity(intent);	
			}
		});
		b2.setOnClickListener(new View.OnClickListener() {
				
		    @Override
			public void onClick(View v) {		
				Intent intent = new Intent(Content.this,Fruit.class);
				intent.putExtra("amount",amount);
				intent.putExtra("name", name);
				startActivity(intent);	
			}
		});
		b3.setOnClickListener(new View.OnClickListener() {
			
		    @Override
			public void onClick(View v) {		
				Intent intent = new Intent(Content.this,Gift.class);
				intent.putExtra("amount",amount);
				intent.putExtra("name", name);
				startActivity(intent);	
			}
		});
		b4.setOnClickListener(new View.OnClickListener() {
			
		    @Override
			public void onClick(View v) {		
				Intent intent = new Intent(Content.this,Stationery.class);
				intent.putExtra("amount",amount);
				intent.putExtra("name", name);
				startActivity(intent);	
			}
		});
		b5.setOnClickListener(new View.OnClickListener() {
			
		    @Override
			public void onClick(View v) {		
				Intent intent = new Intent(Content.this,Spices.class);
				intent.putExtra("amount",amount);
				intent.putExtra("name", name);
				startActivity(intent);	
			}
		});
		b6.setOnClickListener(new View.OnClickListener() {
			
		    @Override
			public void onClick(View v) {		
				Intent intent = new Intent(Content.this,Cool.class);
				intent.putExtra("amount",amount);
				intent.putExtra("name", name);
				startActivity(intent);	
			}
		});
        b7.setOnClickListener(new View.OnClickListener() {
			
		    @Override
			public void onClick(View v) {		
				Intent intent = new Intent(Content.this,ShoppingCart.class);
				intent.putExtra("amount",amount);
				intent.putExtra("name", name);
				startActivity(intent);	
			}
		});
		
	}
	

}
