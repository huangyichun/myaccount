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
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		sp = getSharedPreferences("config", MODE_PRIVATE);
		//判断是否为新的一个月
		int month = DateUtil.getMonth();
		Editor editor = sp.edit();
		int m = sp.getInt("month", 0);

		if (month != m) {
			
			editor.putFloat("budget", 0);
			editor.putInt("month", month);
		}
		editor.commit();
		Intent intent = new Intent(MainActivity.this,
				HomeActivity.class);
		startActivity(intent);
		finish();
		
	}
}
