package com.example.myaccount.activity;

import java.text.ParseException;

import com.example.myaccount.R;
import com.example.myaccount.db.MyAccountOpenHelper;
import com.example.myaccount.model.WeekConsume;
import com.example.myaccount.util.DateUtil;
import com.example.myaccount.util.MathUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeActivity extends Activity implements OnClickListener {
	private TextView tv_date1;
	private TextView tv_date2;
	private MyAccountOpenHelper dbHelper;
	private SharedPreferences sp;
	private TextView tv_set_output;
	private TextView tv_set_input;
	private TextView tv_set_budget;
	private TextView tv_set_budget_2;
	private TextView tv_set_budget_1;
	private RelativeLayout rl_input;
	private RelativeLayout rl_note;
	private RelativeLayout rl_budget;
	private RelativeLayout rl_today;
	private Intent intent;
	private RelativeLayout rl_week;
	private RelativeLayout rl_month;
	private TextView tv_year_time;
	private TextView tv_today_time;
	private TextView tv_month_time;
	private TextView tv_week_time;
	private double todayInput;
	private double todayOutput;
	private double weekInput;
	private double weekOutput;
	private double monthInput;
	private double monthOutput;
	private double yearInput;
	private double yearOutput;
	private int year = DateUtil.getYear();
	private int month = DateUtil.getMonth();
	private int day = DateUtil.getDay();
	private int week;
	private TextView tv_today_input;
	private TextView tv_today_output;

	private TextView tv_week_input;
	private TextView tv_week_output;

	private TextView tv_month_input;
	private TextView tv_month_output;

	private TextView tv_year_input;
	private TextView tv_year_output;
	private RelativeLayout rl_year;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_layout);

		// 初始化控件
		init();

		tv_date1.setText("" + month);
		tv_date2.setText("/" + year);

		rl_input.setOnClickListener(this);
		rl_note.setOnClickListener(this);
		rl_budget.setOnClickListener(this);
		rl_today.setOnClickListener(this);
		rl_week.setOnClickListener(this);
		rl_month.setOnClickListener(this);
		rl_year.setOnClickListener(this);
		
	}

	/**
	 * 初始化控件
	 */
	private void init() {

		tv_date1 = (TextView) findViewById(R.id.tv_date1);
		tv_date2 = (TextView) findViewById(R.id.tv_date2);
		tv_set_input = (TextView) findViewById(R.id.tv_set_input);
		tv_set_output = (TextView) findViewById(R.id.tv_set_output);
		tv_set_budget = (TextView) findViewById(R.id.tv_set_budget);
		tv_set_budget_1 = (TextView) findViewById(R.id.tv_set_budget_1);
		tv_set_budget_2 = (TextView) findViewById(R.id.tv_set_budget_2);
		rl_input = (RelativeLayout) findViewById(R.id.rl_input);
		rl_note = (RelativeLayout) findViewById(R.id.rl_note);
		rl_budget = (RelativeLayout) findViewById(R.id.rl_budget);
		rl_today = (RelativeLayout) findViewById(R.id.rl_today);
		rl_week = (RelativeLayout) findViewById(R.id.rl_week);
		rl_month = (RelativeLayout) findViewById(R.id.rl_month);
		tv_year_time = (TextView) findViewById(R.id.tv_year_time);
		tv_month_time = (TextView) findViewById(R.id.tv_month_time);
		tv_week_time = (TextView) findViewById(R.id.tv_week_time);
		tv_today_time = (TextView) findViewById(R.id.tv_today_time);

		tv_today_input = (TextView) findViewById(R.id.tv_today_input);
		tv_today_output = (TextView) findViewById(R.id.tv_today_output);

		tv_week_input = (TextView) findViewById(R.id.tv_week_input);
		tv_week_output = (TextView) findViewById(R.id.tv_week_output);

		tv_month_input = (TextView) findViewById(R.id.tv_month_input);
		tv_month_output = (TextView) findViewById(R.id.tv_month_output);

		tv_year_input = (TextView) findViewById(R.id.tv_year_input);
		tv_year_output = (TextView) findViewById(R.id.tv_year_output);
		rl_year = (RelativeLayout) findViewById(R.id.rl_year);

		dbHelper = new MyAccountOpenHelper(this, "Account.db", null, 1);
		dbHelper.getWritableDatabase();
		sp = getSharedPreferences("config", MODE_PRIVATE);

		int year = DateUtil.getYear();
		int month = DateUtil.getMonth();
		int day = DateUtil.getDay();
		if (month < 10 && day < 10) {

			tv_today_time.setText(year + "年" + "0" + month + "月" + "0" + day
					+ "日");
			tv_month_time.setText("0" + month + "月" + "01" + "日" + "-" + "0"
					+ month + "月" + DateUtil.getLastDay(year, month) + "日");
		} else if (month < 10 && day >= 10) {
			tv_today_time.setText(year + "年" + "0" + month + "月" + day + "日");
			tv_month_time.setText("0" + month + "月" + "01" + "日" + "-" + "0"
					+ month + "月" + DateUtil.getLastDay(year, month) + "日");
		} else if (day < 10 && month >= 10) {
			tv_today_time.setText(year + "年" + month + "月" + "0" + day + "日");
			tv_month_time.setText(month + "月" + "01" + "日" + "-" + month + "月"
					+ DateUtil.getLastDay(year, month) + "日");
		} else {
			tv_today_time.setText(year + "年" + month + "月" + day + "日");
			tv_month_time.setText(month + "月" + "01" + "日" + "-" + month + "月"
					+ DateUtil.getLastDay(year, month) + "日");
		}

		try {
			tv_week_time.setText(DateUtil.getStWeekEnd(year, month, day, 2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tv_year_time.setText("01" + "月" + "01" + "日" + "-" + "12" + "月" + "31"
				+ "日");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_input:
			intent = new Intent(HomeActivity.this, MonthActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_note:
			intent = new Intent(HomeActivity.this, NewOutputActivity.class);
			startActivity(intent);

			break;
		case R.id.rl_budget:
			intent = new Intent(HomeActivity.this, BudgetActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_today:
			intent = new Intent(HomeActivity.this, DayActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_week:
			intent = new Intent(HomeActivity.this,
					WeekExpandableListViewActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_month:
			intent = new Intent(HomeActivity.this,
					MonthExpandableListViewActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_year:
			intent = new Intent(HomeActivity.this,
					YearExpandableListViewActivity.class);
			startActivity(intent);
			break;

		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		try {
			week = DateUtil.getWeek(year + "-" + month + "-" + day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (DateUtil.isSunday(year, month, day)) {
				week = week - 1;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		weekOutput = 0;
		weekInput = 0;
		todayOutput = 0;
		todayInput = 0;
		monthOutput = 0;
		monthInput = 0;
		yearOutput = 0;
		yearInput = 0;
		searchData();
		// 设置收入和支出总额
		tv_set_input.setText(MathUtil.setTwoPoint(monthInput));
		tv_set_output.setText(MathUtil.setTwoPoint(monthOutput));
		// 设置预算
		double budget = sp.getFloat("budget", 0);
		if (budget > 0) {
			tv_set_budget_1 = (TextView) findViewById(R.id.tv_set_budget_1);
			tv_set_budget.setText(MathUtil.setTwoPoint(budget - monthOutput));
			tv_set_budget.setVisibility(View.VISIBLE);
			tv_set_budget_1.setVisibility(View.VISIBLE);
			tv_set_budget_2.setVisibility(View.INVISIBLE);
		} else {
			tv_set_budget.setVisibility(View.INVISIBLE);
			tv_set_budget_1.setVisibility(View.INVISIBLE);
			tv_set_budget_2.setVisibility(View.VISIBLE);
		}

		tv_today_input.setText(MathUtil.setTwoPoint(todayInput));
		tv_today_output.setText(MathUtil.setTwoPoint(todayOutput));

		tv_week_input.setText(MathUtil.setTwoPoint(weekInput));
		tv_week_output.setText(MathUtil.setTwoPoint(weekOutput));

		tv_month_input.setText(MathUtil.setTwoPoint(monthInput));
		tv_month_output.setText(MathUtil.setTwoPoint(monthOutput));

		tv_year_input.setText(MathUtil.setTwoPoint(yearInput));
		tv_year_output.setText(MathUtil.setTwoPoint(yearOutput));

	}

	private void searchData() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor;
		cursor = db.query("OutputAccount", null,
				"output_year = ? and output_month = ? and output_week = ?",
				new String[] { "" + year, "" + month, "" + week }, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("output_money"));
				weekOutput = weekOutput + money;
			} while (cursor.moveToNext());

		}
		cursor = db.query("OutputAccount", null,
				"output_year = ? and output_month = ? and output_date = ?",
				new String[] { "" + year, "" + month, "" + day }, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("output_money"));
				todayOutput = todayOutput + money;
			} while (cursor.moveToNext());

		}

		cursor = db.query("OutputAccount", null,
				"output_year = ? and output_month = ? ", new String[] {
						"" + year, "" + month }, null, null, null);
		if (cursor.moveToFirst()) {
			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("output_money"));
				monthOutput = monthOutput + money;
			} while (cursor.moveToNext());

		}

		cursor = db.query("OutputAccount", null, "output_year = ? ",
				new String[] { "" + year }, null, null, null);
		if (cursor.moveToFirst()) {
			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("output_money"));
				yearOutput = yearOutput + money;
			} while (cursor.moveToNext());

		}

		cursor = db.query("InputAccount", null,
				"input_year = ? and input_month = ? and input_week = ?",
				new String[] { "" + year, "" + month, "" + week }, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("input_money"));
				weekInput = weekInput + money;

			} while (cursor.moveToNext());
		}

		cursor = db.query("InputAccount", null,
				"input_year = ? and input_month = ? and input_date = ?",
				new String[] { "" + year, "" + month, "" + day }, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("input_money"));
				todayInput = todayInput + money;

			} while (cursor.moveToNext());
		}

		cursor = db.query("InputAccount", null,
				"input_year = ? and input_month = ? ", new String[] {
						"" + year, "" + month }, null, null, null);
		if (cursor.moveToFirst()) {
			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("input_money"));
				monthInput = monthInput + money;

			} while (cursor.moveToNext());
		}

		cursor = db.query("InputAccount", null, "input_year = ? ",
				new String[] { "" + year }, null, null, null);
		if (cursor.moveToFirst()) {
			do {

				double money = cursor.getFloat(cursor
						.getColumnIndex("input_money"));
				yearInput = yearInput + money;

			} while (cursor.moveToNext());
		}
		cursor.close();
	}

}
