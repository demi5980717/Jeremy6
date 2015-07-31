package com.example.jeremy6;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Title extends Activity {
	private Button btnOK;
	private EditText edttitle;
	SharedPreferences preference;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);
		
		btnOK=(Button)findViewById(R.id.button1);
		btnOK.setOnClickListener(buttonResponse);
		edttitle=(EditText)findViewById(R.id.editText1);
		
		preference=getSharedPreferences("title",MODE_PRIVATE);
		
		

	}
	
	private OnClickListener buttonResponse=new OnClickListener(){
		
		@Override
		public void onClick(View v){
			//儲存資料
			preference.edit()
			.putString("title",edttitle.getText().toString())
			.commit();
			//跳轉業面
			Intent intent=new Intent();
			intent.setClass(Title.this,Palette.class);
			startActivity(intent);
			finish();
		}	
	};
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.title, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
