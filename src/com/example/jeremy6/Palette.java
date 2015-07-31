package com.example.jeremy6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.example.jeremy6.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Palette extends Activity {
	private ImageView iv;
	private Bitmap baseBitmap;
	private Canvas canvas;
	private Paint paint;
	private Button btnSave,btnClear;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_palette);
		
		btnSave=(Button)findViewById(R.id.button1);
		btnClear=(Button)findViewById(R.id.button2);
		
		
		btnSave.setOnClickListener(buttonResponse);
		btnSave.setOnClickListener(buttonResponse);
		
		
		 this.iv = (ImageView) this.findViewById(R.id.iv);
		  // 创建一张空白图片
		  baseBitmap = Bitmap.createBitmap(480, 640, Bitmap.Config.ARGB_8888);
		  // 创建一张画布
		  canvas = new Canvas(baseBitmap);
		  // 画布背景为白色
		  canvas.drawColor(Color.WHITE);
		  // 创建画笔
		  paint = new Paint();
		  // 画笔颜色为黑色
		  paint.setColor(Color.BLACK);
		  // 宽度5个像素
		  paint.setStrokeWidth(5);
		  // 先将灰色背景画上
		  canvas.drawBitmap(baseBitmap, new Matrix(), paint);
		  iv.setImageBitmap(baseBitmap);
		  
		  
		 
		  iv.setOnTouchListener(new OnTouchListener() { 
			  int startX; 
			  int startY;
			  
			  
			  @Override
			  public boolean onTouch(View v, MotionEvent event) { 
				  switch (event.getAction()) {  
				  case MotionEvent.ACTION_DOWN: 
					  // 获取手按下时的坐标 
					  startX = (int) event.getX();  
					  startY = (int) event.getY();
					  break; 
				  case MotionEvent.ACTION_MOVE:  
					// 获取手移动后的坐标
					  int stopX = (int) event.getX(); 
					  int stopY = (int) event.getY();  
					// 在开始和结束坐标间画一条线  
					  canvas.drawLine(startX, startY, stopX, stopY, paint); 
					// 实时更新开始坐标
					  startX = (int) event.getX(); 
					  startY = (int) event.getY();  
					  iv.setImageBitmap(baseBitmap);  
					  break;  
					  
				  }
				  return true;
			  }
		  });
	}
	
	private OnClickListener buttonResponse=new 
			OnClickListener(){
		@Override
		public void onClick(View v){
			switch(v.getId())
			{
			case R.id.button1:
				save();
				Intent intent=new Intent();
				intent.setClass(Palette.this,Guess.class);
				startActivity(intent);
				finish();
				break;
			case R.id.button2:
				clear();
				break;
			default:
				break;
			}
		}
		
	};
			
	
	public void save() {

		  try {		
			                    //返回 File ，得到外部儲存目錄即 SDCard
		   File file = new File(Environment.getExternalStorageDirectory(),
		     System.currentTimeMillis() + ".png");
		   OutputStream stream = new FileOutputStream(file);
		   baseBitmap.compress(CompressFormat.PNG, 100, stream);
		   stream.close();
		   // 模拟一个广播，通知系统sdcard被挂载
		   Intent intent = new Intent();
		   intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
		   intent.setData(Uri.fromFile(Environment
		     .getExternalStorageDirectory()));
		   sendBroadcast(intent);
		   Toast.makeText(Palette.this, "SUCCESSFUL",Toast.LENGTH_SHORT).show();
		   
		  } catch (Exception e) {
		   Toast.makeText(Palette.this, "FAIL",Toast.LENGTH_SHORT).show();
		   e.printStackTrace();
		  }

		 }

	 /**
	     * 清除画板
	      */
	    protected void clear() {
	         // 手动清除画板的绘图，重新创建一个画板
	         if (baseBitmap != null) {
	             baseBitmap = Bitmap.createBitmap(iv.getWidth(),
	                     iv.getHeight(), Bitmap.Config.ARGB_8888);
	             canvas = new Canvas(baseBitmap);
	             canvas.drawColor(Color.WHITE);
	             iv.setImageBitmap(baseBitmap);
	             Toast.makeText(Palette.this, "清除画板成功，可以重新开始绘图", 
	            		 Toast.LENGTH_SHORT).show();
	        }
	     }
	    
	
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.palette, menu);
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
