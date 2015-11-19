package com.example.myaccount.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.myaccount.R;
import com.example.myaccount.adapter.BudgetAdapter;
import com.example.myaccount.db.MyAccountOpenHelper;
import com.example.myaccount.model.ShowOutputAccount;
import com.example.myaccount.util.DateUtil;
import com.example.myaccount.util.MathUtil;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends Activity implements OnClickListener {
	private SharedPreferences sp;
	private static final String TAG = "BudgetActivity";
	private MyAccountOpenHelper dbHelper;
	private List<ShowOutputAccount> accountList = new ArrayList<ShowOutputAccount>();
	private double budget;
	private ImageView iv_back;
	private TextView tv_back;
	private EditText set_budget;
	private TextView tv_yiyong_1;
	private TextView tv_keyong;
	private ImageView iv_update;
	private RelativeLayout rl_budget_layout;
	private ListView lv_budge;
	public static final int UPDATE_TEXT = 1;
	private double outputMoney;
	private double keYong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.budget_layout);
		
		dbHelper = new MyAccountOpenHelper(this, "Account.db", null, 1);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		// 初始化控件
		init();
		// 初始化数据
		initAccount();
		budget = sp.getFloat("budget", 0);
		set_budget.setText(MathUtil.setTwoPoint(budget) + "");
		keYong = budget - outputMoney;
		tv_keyong.setText(MathUtil.setTwoPoint(keYong));
		tv_yiyong_1.setText(MathUtil.setTwoPoint(outputMoney));
		BudgetAdapter adapter = new BudgetAdapter(BudgetActivity.this,
				R.layout.budget_item_layout, accountList);
		ListView listView = (ListView) findViewById(R.id.lv_budge);
		listView.setAdapter(adapter);

	}

	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back);
		tv_back = (TextView) findViewById(R.id.tv_back);
		set_budget = (EditText) findViewById(R.id.set_budget);
		tv_yiyong_1 = (TextView) findViewById(R.id.tv_yiyong_1);
		tv_keyong = (TextView) findViewById(R.id.tv_keyong);
		iv_update = (ImageView) findViewById(R.id.iv_update);
		rl_budget_layout = (RelativeLayout) findViewById(R.id.rl_budget_layout);
		lv_budge = (ListView) findViewById(R.id.lv_budge);
		
		
		tv_yiyong_1.setText(MathUtil.setTwoPoint(outputMoney));
		keYong = budget - outputMoney;
		tv_keyong.setText(MathUtil.setTwoPoint(keYong));
		
		iv_back.setOnClickListener(this);
		tv_back.setOnClickListener(this);
		set_budget.setOnClickListener(this);
		iv_update.setOnClickListener(this);
		// 使EditText 失去焦点
		rl_budget_layout.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				rl_budget_layout.setFocusable(true);
				rl_budget_layout.setFocusableInTouchMode(true);
				rl_budget_layout.requestFocus();

				((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(getCurrentFocus()
								.getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				if(TextUtils.isEmpty(set_budget.getText())){
					set_budget.setText("0.00");
				}
				return false;
			}
		});
		
		lv_budge.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				rl_budget_layout.setFocusable(true);
				rl_budget_layout.setFocusableInTouchMode(true);
				rl_budget_layout.requestFocus();
				
				((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
						.hideSoftInputFromWindow(getCurrentFocus()
								.getWindowToken(),
								InputMethodManager.HIDE_NOT_ALWAYS);
				if(TextUtils.isEmpty(set_budget.getText())){
					set_budget.setText("0.00");
				}
				return false;
			}
		});

	}

	private Handler handler = new Handler(){
		
		public void handleMessage(Message msg) {
			switch (msg.what){
			case UPDATE_TEXT:
				budget = sp.getFloat("budget", 0);
				keYong = budget - outputMoney;
				tv_keyong.setText(MathUtil.setTwoPoint(keYong));
				tv_yiyong_1.setText(MathUtil.setTwoPoint(outputMoney));
				break;
				default :
					break;
			}
		}
	};
	
	/**
	 * 设置点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:
		case R.id.tv_back:
			finish();
			break;
		case R.id.set_budget:
			
			set_budget.setText("");
			
			break;
		
	
		case R.id.iv_update:
			Editor editor = sp.edit();
			String str = set_budget.getText().toString();
			if(TextUtils.isEmpty(str)){
				Toast.makeText(BudgetActivity.this, "输入的金额不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			budget = (float) Double.parseDouble(str);
			
			
			Log.d(TAG, budget+"");
			editor.putFloat("budget", (float) budget);
			editor.commit();
			new Thread(new Runnable() {
				@Override
				public void run() {
					Message msg = new Message();
					msg.what = UPDATE_TEXT;
					handler.sendMessage(msg);
				}
			}).start();
			//将软盘隐藏
			((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
			.hideSoftInputFromWindow(getCurrentFocus()
					.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
			break;
		}

	}

	/**
	 * 初始化显示的数据
	 */
	private void initAccount() {
		// 获得今天的时间
		String sMoney;
		int year = DateUtil.getYear();
		int month = DateUtil.getMonth();

		Log.d(TAG, year + "" + month);
		String item1[] = { "食品酒水	", "衣服饰品", "居家物业", "行车交通", "交流通讯", "休闲娱乐",
				"学习进修", "人情往来", "医疗保健", "金融保险", "其他杂项" };
		int[] imageIds = { R.drawable.icon_spjs, R.drawable.icon_yfsp,
				R.drawable.icon_jjwy, R.drawable.icon_xcjt_sjcfy,
				R.drawable.icon_jltx, R.drawable.icon_xxyl,
				R.drawable.icon_xxjx, R.drawable.icon_rqwl,
				R.drawable.icon_ylbj, R.drawable.icon_jrtz_bxsr,
				R.drawable.icon_zysr_jzsr };
		double[] moneys = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		// 查询数据库，并且将数据存储到List集合中
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("OutputAccount", null,
				"output_year = ? and output_month = ?", new String[] {
						"" + year, "" + month }, null, null, null);
		Log.d(TAG, "运行到这里了2");

		if (cursor.moveToFirst()) {
			Log.d(TAG, "运行到这里了3");
			do {
				String name = cursor.getString(cursor
						.getColumnIndex("output_type"));
				Log.d(TAG, "运行到这里了4");
				double money = cursor.getFloat(cursor
						.getColumnIndex("output_money"));
				outputMoney = outputMoney + money;
				Log.d(TAG, name + money);

				// 判断类型，选择图片"食品酒水	", "衣服饰品", "居家物业", "行车交通", "交流通讯",
				// "休闲娱乐", "学习进修", "人情往来", "医疗保健", "金融保险", "其他杂项"
				switch (name) {
				case "食品酒水":
					moneys[0] = moneys[0] + money;
					break;
				case "衣服饰品":
					moneys[1] = moneys[1] + money;
					break;
				case "居家物业":
					moneys[2] = moneys[2] + money;
					break;
				case "行车交通":
					moneys[3] = moneys[3] + money;
					break;
				case "交流通讯":
					moneys[4] = moneys[4] + money;
					break;
				case "休闲娱乐":
					moneys[5] = moneys[5] + money;
					break;
				case "学习进修":
					moneys[6] = moneys[6] + money;
					break;
				case "人情往来":
					moneys[7] = moneys[7] + money;
					break;
				case "医疗保健":
					moneys[8] = moneys[8] + money;
					break;
				case "金融保险":
					moneys[9] = moneys[9] + money;
					break;

				default:
					moneys[10] = moneys[10] + money;
					break;
				}

			} while (cursor.moveToNext());

		}
		cursor.close();
		for (int i = 0; i < 11; i++) {
			String typeName = item1[i];
			int imageId = imageIds[i];
			if (moneys[i] > 0) {
				sMoney = "-" + MathUtil.setTwoPoint(moneys[i]);

			} else {
				sMoney = "" + MathUtil.setTwoPoint(moneys[i]);
			}

			ShowOutputAccount account = new ShowOutputAccount(typeName, sMoney,
					imageId);

			accountList.add(account);
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		
		
		
	}

}
