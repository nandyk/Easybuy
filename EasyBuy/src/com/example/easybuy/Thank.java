package com.example.easybuy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Thank extends Activity{
	TextView t1;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
	      setContentView(R.layout.thank);
          Button b1=(Button)findViewById(R.id.b1);
   	      b1.setOnClickListener(new View.OnClickListener() {
   		
   		       @Override
   		       public void onClick(View v) {
   			   // TODO Auto-generated method stub
   		    	Toast.makeText(getApplicationContext(),"logout successfully!!!", Toast.LENGTH_LONG).show();
   		       }
   	     });
      }
}
