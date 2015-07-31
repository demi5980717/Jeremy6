package com.example.jeremy6;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends Activity {
	private Button butStart;
	private int member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //先預設已滿員
        member=10;
        
        if(member==10){
        	new AlertDialog.Builder(MainActivity.this)
        	.setTitle("comfirm to start")
        	.setIcon(R.drawable.ic_launcher)
        	.setMessage("Start game?")
        	.setPositiveButton("YES",new 
        			OnClickListener()
        	{
        		public void onClick(DialogInterface dialoginterface,int i)
        		{
        			Intent intent=new Intent();
    				intent.setClass(MainActivity.this,Title.class);
    				startActivity(intent);
    				finish();
        		}
        	})
        	.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
