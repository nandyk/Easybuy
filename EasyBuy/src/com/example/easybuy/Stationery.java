package com.example.easybuy;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
 
public class Stationery extends Activity {
    int amount;
    String name;
    String[] countries = new String[] {
        "Pen & Pencil",
        "Note book",
        "Calculator"
    };
    int[] flags = new int[]{
        R.drawable.pen,
        R.drawable.notebook,
        R.drawable.calculator
       };
    String[] currency = new String[]{
        "Rs.30",
        "Rs.50",
        "Rs.200"
       };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stationery);
        Intent mIntent = getIntent();
        amount = mIntent.getIntExtra("amount",0);
        name = mIntent.getExtras().getString("name");
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
 
        for(int i=0;i<3;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt",countries[i]);
            hm.put("cur",currency[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }
        String[] from = { "flag","txt","cur" };
        int[] to = { R.id.flag,R.id.txt,R.id.cur};
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);
        ListView listView = ( ListView ) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),"clicked", Toast.LENGTH_LONG).show();
				if(position==0)
				{
				     Intent next = new Intent(Stationery.this,Pen.class);
				     next.putExtra("amount",amount);
				     next.putExtra("name", name);
                     startActivity(next);
				}
				else if(position==1)
				{
				     Intent next = new Intent(Stationery.this,Notebook.class);
				     next.putExtra("amount",amount);
				     next.putExtra("name", name);
                     startActivity(next);
				}
				else 
				{
				     Intent next = new Intent(Stationery.this,Calculator.class);
				     next.putExtra("amount",amount);
				     next.putExtra("name", name);
                     startActivity(next);
				}
			}
		});
        Button b1=(Button)findViewById(R.id.b1);
      	 b1.setOnClickListener(new View.OnClickListener() {
      		
      		  @Override
      		  public void onClick(View v) {
      			// TODO Auto-generated method stub
      		      Intent i = new Intent(Stationery.this,Content.class);
      		      i.putExtra("amount",amount);
      		      i.putExtra("name", name);
      		      startActivity(i);
      		     }
      	   });
    }
}