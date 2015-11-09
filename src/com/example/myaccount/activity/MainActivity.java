package com.example.myaccount.activity;

import com.example.myaccount.R;
import com.example.myaccount.R.layout;
import com.example.myaccount.db.MyAccountOpenHelper;
import com.example.myaccount.util.DateUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private ImageView iv_start;
	private long nowTime;
	private long afterTime;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		iv_start = (ImageView) findViewById(R.id.iv_start);
		iv_start.setImageResource(R.drawable.start);
		 nowTime = System.currentTimeMillis();
		 sp = getSharedPreferences("config", MODE_PRIVATE);
		 int month = DateUtil.getMonth();
		 Log.d(TAG, month+"");
		 Editor editor = sp.edit();
		 int m = sp.getInt("month", 0);
		 
		 if(month != m){
			 editor.putFloat("output", 0);
			 editor.putFloat("input", 0);
			 editor.putFloat("budget", 0);
			 editor.putInt("month", month);
		 }
		 editor.commit();
		 
	/*	
		int year = DateUtil.getYear();
		int month = DateUtil.getMonth();

		sp = getSharedPreferences("config", MODE_PRIVATE);
		dbHelper = new MyAccountOpenHelper(this, "Account.db", null, 1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		Log.d(TAG, "���е�������1");
		Cursor cursor = db.query("OutputAccount", null,
				"output_year = ? and output_month = ?", new String[] {
						"" + year, "" + month }, null, null, null);

		if (cursor.moveToFirst()) {

			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("output_money"));
				// �ж����ͣ�ѡ��ͼƬ"ʳƷ��ˮ	", "�·���Ʒ", "�Ӽ���ҵ", "�г���ͨ", "����ͨѶ",
				// "��������", "ѧϰ����", "��������", "ҽ�Ʊ���", "���ڱ���", "��������"
				outputMoney = money + outputMoney;

			} while (cursor.moveToNext());
		}
		Log.d(TAG, "���е�������2");
		
		
		 cursor = db.query("InputAccount", null,
				"input_year = ? and input_month = ?", new String[] { "" + year,
						"" + month }, null, null, null);

		if (cursor.moveToFirst()) {

			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("Input_money"));
				// �ж����ͣ�ѡ��ͼƬ"ʳƷ��ˮ	", "�·���Ʒ", "�Ӽ���ҵ", "�г���ͨ", "����ͨѶ",
				// "��������", "ѧϰ����", "��������", "ҽ�Ʊ���", "���ڱ���", "��������"
				inputMoney = money + inputMoney;

			} while (cursor.moveToNext());

		}
		
		cursor.close();
		Log.d(TAG, "���е�������3");
		Editor editor = sp.edit();
		editor.putFloat("input", (float) inputMoney);
		editor.putFloat("output", (float) outputMoney);
		editor.commit();*/
		
		afterTime = System.currentTimeMillis();
		long dTime = afterTime - nowTime;
		if(dTime < 2000){
        Handler handler = new Handler();  
      
        handler.postDelayed(new Runnable() {  //ʹ��handler��postDelayedʵ����ʱ��ת  
            
        	
        	
        	
			@Override
			public void run() {
				Intent intent = new Intent(MainActivity.this, HomeActivity.class);
	    		startActivity(intent);
	    		finish();      
	            }
        }, 2000-dTime);//2�����ת��Ӧ��������MainActivity  
          
		}
		
		

	}
}
