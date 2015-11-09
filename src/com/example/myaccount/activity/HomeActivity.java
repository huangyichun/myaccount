package com.example.myaccount.activity;

import com.example.myaccount.R;
import com.example.myaccount.db.MyAccountOpenHelper;
import com.example.myaccount.util.MathUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_layout);
		// 获取系统时间
		Time t = new Time("GMT+8");
		t.setToNow();
		int year = t.year;
		int month = t.month + 1;

		// 初始化控件
		init();

		tv_date1.setText("" + month);
		tv_date2.setText("/" + year);
		

		rl_input.setOnClickListener(this);
		rl_note.setOnClickListener(this);
		rl_budget.setOnClickListener(this);
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

		dbHelper = new MyAccountOpenHelper(this, "Account.db", null, 1);
		dbHelper.getWritableDatabase();
		sp = getSharedPreferences("config", MODE_PRIVATE);
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
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// 获取收入和支出总额
		double inputMoney = sp.getFloat("input", 0);
		double outputMoney = sp.getFloat("output", 0);
		// 设置收入和支出总额
		tv_set_input.setText(MathUtil.setTwoPoint(inputMoney));
		tv_set_output.setText(MathUtil.setTwoPoint(outputMoney));
		// 设置预算
		double budget = sp.getFloat("budget", 0);
		if (budget > 0) {
			tv_set_budget_1 = (TextView) findViewById(R.id.tv_set_budget_1);
			tv_set_budget.setText(MathUtil.setTwoPoint(budget-outputMoney));
			tv_set_budget.setVisibility(View.VISIBLE);
			tv_set_budget_1.setVisibility(View.VISIBLE);
			tv_set_budget_2.setVisibility(View.INVISIBLE);
		}else{
			tv_set_budget.setVisibility(View.INVISIBLE);
			tv_set_budget_1.setVisibility(View.INVISIBLE);
			tv_set_budget_2.setVisibility(View.VISIBLE);
		}
	}

}
