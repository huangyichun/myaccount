package com.example.myaccount.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.myaccount.R;
import com.example.myaccount.adapter.DayAdapter;
import com.example.myaccount.db.MyAccountOpenHelper;
import com.example.myaccount.model.DayConsume;
import com.example.myaccount.util.DateUtil;
import com.example.myaccount.util.MathUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DayActivity extends Activity implements OnClickListener {

	private ImageView iv_back;
	private TextView tv_back;
	private ImageView iv_update;
	private TextView set_budget;
	private TextView tv_yiyong_1;
	private TextView tv_keyong;
	private TextView tv_month_time;
	private TextView tv_week_time;
	private static final String TAG = "BudgetActivity";
	private MyAccountOpenHelper dbHelper;
	private List<DayConsume> accountList = new ArrayList<DayConsume>();
	private int year;
	private int month;
	private int day;
	private double inputMoney;
	private double outputMoney;
	private DayConsume dayConsume;
	private String week;
	private TextView lv_budge_none;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.day_layout);

	}

	private void init() {
		// 初始化控件
		tv_back = (TextView) findViewById(R.id.tv_back);
		iv_update = (ImageView) findViewById(R.id.iv_update);
		set_budget = (TextView) findViewById(R.id.set_budget);
		tv_yiyong_1 = (TextView) findViewById(R.id.tv_yiyong_1);
		tv_keyong = (TextView) findViewById(R.id.tv_keyong);

		tv_month_time = (TextView) findViewById(R.id.tv_month_time);
		tv_week_time = (TextView) findViewById(R.id.tv_week_time);
		lv_budge_none = (TextView) findViewById(R.id.lv_budge_none);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		
		tv_back.setOnClickListener(this);
		iv_update.setOnClickListener(this);
		iv_back.setOnClickListener(this);
	}

	private void initAccount() {
		// 初始化控件

		day = DateUtil.getDay();
		year = DateUtil.getYear();
		month = DateUtil.getMonth();
		week = DateUtil.getDayOfWeek();
		
			
		

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("OutputAccount", null,
				"output_year = ? and output_month = ? and output_date = ?",
				new String[] { "" + year, "" + month, "" + day }, null, null,
				null);

		Log.d(TAG, "运行到这里了2");

		if (cursor.moveToFirst()) {
			Log.d(TAG, "运行到这里了3");
			do {
				String type = cursor.getString(cursor
						.getColumnIndex("output_type"));
				double money = cursor.getFloat(cursor
						.getColumnIndex("output_money"));
				String account = cursor.getString(cursor
						.getColumnIndex("output_notes"));
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				if(account.length() > 5){
					account = account.substring(0, 5)+"...";
				}
				outputMoney = outputMoney + money;

				dayConsume = new DayConsume(type, "备注:"+account, "" +MathUtil.setTwoPoint(money) , id);
				accountList.add(dayConsume);

			} while (cursor.moveToNext());

		}

		cursor = db.query("InputAccount", null,
				"input_year = ? and input_month = ? and input_date = ?",
				new String[] { "" + year, "" + month, "" + day }, null, null,
				null);

		Log.d(TAG, "运行到这里了4");
		if (cursor.moveToFirst()) {
			do {
				String type = cursor.getString(cursor
						.getColumnIndex("input_type"));
				double money = cursor.getFloat(cursor
						.getColumnIndex("input_money"));
				String account = cursor.getString(cursor
						.getColumnIndex("input_notes"));
				if(account.length() > 5){
					account = account.substring(0, 5)+"...";
				}
				inputMoney = inputMoney + money;
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				dayConsume = new DayConsume(type, "备注:"+account, "" + MathUtil.setTwoPoint(money), id);
				accountList.add(dayConsume);

			} while (cursor.moveToNext());

		}

		cursor.close();
		Log.d(TAG, "运行到这里了5");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
		case R.id.tv_back:
			finish();
			break;
		case R.id.iv_update:
			Intent intent = new Intent(DayActivity.this,
					NewOutputActivity.class);
			startActivity(intent);
			break;
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		dbHelper = new MyAccountOpenHelper(this, "Account.db", null, 1);

		// 每次开始时要多容器，和支付收入总额进行初始化
		accountList.clear();
		outputMoney = 0;
		inputMoney = 0;
		// 获取控件
		init();

		// 初始化数据
		initAccount();
		DayAdapter adapter = new DayAdapter(DayActivity.this,
				R.layout.day_item_layout, accountList);

		ListView listView = (ListView) findViewById(R.id.lv_budge);

		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				DayConsume dayConsume = accountList.get(position);
				String type = dayConsume.getType();
				switch (type) {
				case "工资收入":
				case "利息收入":
				case "加班收入":
				case "奖金收入":
				case "投资收入":
				case "兼职收入":
				case "其他收入":
					 intent = new Intent(DayActivity.this,
							InputModifyActivity.class);
					intent.putExtra("dayConsume", dayConsume);
					startActivity(intent);
					break;
				default:
					 intent = new Intent(DayActivity.this,
							OutputModifyActivity.class);
					intent.putExtra("dayConsume", dayConsume);
					startActivity(intent);
					break;
				}
			}

		});

		set_budget.setText(MathUtil.setTwoPoint(inputMoney - outputMoney));
		tv_yiyong_1.setText(MathUtil.setTwoPoint(inputMoney));
		tv_keyong.setText(MathUtil.setTwoPoint(outputMoney));

		tv_month_time.setText(day + "");
		tv_week_time.setText(week);
		
		tv_back.setText("今天" + month + "月" + day + "日");

		if (accountList.isEmpty()) {
			lv_budge_none.setVisibility(View.VISIBLE);

		} else {
			lv_budge_none.setVisibility(View.INVISIBLE);

		}

	}
}
