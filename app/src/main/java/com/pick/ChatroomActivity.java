package com.pick;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ChatroomActivity extends AppCompatActivity {
	   ListView m_ListView;
	   CustomAdapter m_Adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_room);
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		final ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setTitle("id");

		m_Adapter = new CustomAdapter();
		m_ListView = (ListView) findViewById(R.id.listView1);

		m_ListView.setAdapter(m_Adapter);
		
		findViewById(R.id.button1).setOnClickListener(new Button.OnClickListener() {
				@Override
				public void onClick(View v) {						
					 EditText editText = (EditText) findViewById(R.id.editText1) ;  
					 String inputValue = editText.getText().toString() ;
					 editText.setText("");
					 refresh(inputValue,0); 				 
				}
			}
		);
		

		findViewById(R.id.button2).setOnClickListener(new Button.OnClickListener() {
				@Override
				public void onClick(View v) {						
					 EditText editText = (EditText) findViewById(R.id.editText1) ;  
					 String inputValue = editText.getText().toString() ;
					 editText.setText("");
					 refresh(inputValue,1); 				 
				}
			}
		);
		
	}



	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// NavUtils.navigateUpFromSameTask(this);
				finish();
				return true;
		}
		return super.onOptionsItemSelected(item);
	};

	private void refresh (String inputValue, int _str) {  
		m_Adapter.add(inputValue,_str) ;  
		m_Adapter.notifyDataSetChanged();  
	} 
}
