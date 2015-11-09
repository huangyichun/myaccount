package com.example.myaccount.activity;

import java.text.ParseException;
import java.util.Calendar;

import com.example.myaccount.R;
import com.example.myaccount.db.MyAccountOpenHelper;
import com.example.myaccount.util.DateUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NewOutputActivity extends Activity implements OnClickListener {

	private MyAccountOpenHelper dbHelper;
	private static final String TAG = "NewOutputActivity";
	private ImageView iv_back;
	private Intent intent;
	private RelativeLayout rl_new_output;
	private EditText et_output_money;
	private RelativeLayout rl_output_type;
	private SharedPreferences sp;
	private TextView tv_output_type;
	private RelativeLayout rl_output_account;
	private TextView tv_output_account;
	private AlertDialog.Builder dialog;
	private RelativeLayout rl_output_time;
	private TextView tv_output_time;
	private TextView tv_contain;
	private Button bt_contain;
	private EditText et_notes;
	private ImageView iv_contain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_output_layout);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		dbHelper = new MyAccountOpenHelper(this, "Account.db", null, 1);
		
		
		// 初始化控件
		init();

		iv_back.setOnClickListener(this);
		rl_new_output.setOnClickListener(this);
		et_output_money.setOnClickListener(this);
		rl_output_type.setOnClickListener(this);
		rl_output_account.setOnClickListener(this);
		rl_output_time.setOnClickListener(this);
		tv_contain.setOnClickListener(this);
		bt_contain.setOnClickListener(this);
		iv_contain.setOnClickListener(this);

	}

	/**
	 * 初始化控件
	 */
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back);
		rl_new_output = (RelativeLayout) findViewById(R.id.rl_new_output);
		et_output_money = (EditText) findViewById(R.id.et_output_money);
		rl_output_type = (RelativeLayout) findViewById(R.id.rl_output_type);
		tv_output_type = (TextView) findViewById(R.id.tv_output_type);
		rl_output_account = (RelativeLayout) findViewById(R.id.rl_output_account);
		tv_output_account = (TextView) findViewById(R.id.tv_output_account);
		rl_output_time = (RelativeLayout) findViewById(R.id.rl_output_time);
		tv_output_time = (TextView) findViewById(R.id.tv_output_time);
		tv_contain = (TextView) findViewById(R.id.tv_contain);
		bt_contain = (Button) findViewById(R.id.bt_contain);
		et_notes = (EditText) findViewById(R.id.et_notes);
		iv_contain = (ImageView) findViewById(R.id.iv_contain);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:

			finish();
			break;
		case R.id.rl_new_output:
			intent = new Intent(NewOutputActivity.this, NewInputActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.et_output_money:
			et_output_money.setText("");

			break;

		case R.id.rl_output_type:
			final String item1[] = { "食品酒水	", "衣服饰品", "居家物业", "行车交通", "交流通讯",
					"休闲娱乐", "学习进修", "人情往来", "医疗保健", "金融保险", "其他杂项" };
			dialog = new AlertDialog.Builder(NewOutputActivity.this);
			dialog.setTitle("请选择消费类型");
			dialog.setSingleChoiceItems(item1, 0,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							tv_output_type.setText(item1[which]);
							dialog.dismiss();
						}
					});
			dialog.setNegativeButton("取消", null);
			dialog.show();
			break;

		case R.id.rl_output_account:
			final String[] item2 = { "现金	", "信用卡", "银行卡", "饭卡", "支付宝", "公交卡",
					"其他" };
			dialog = new AlertDialog.Builder(NewOutputActivity.this);
			dialog.setTitle("请选择消费类型");
			dialog.setSingleChoiceItems(item2, 0,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							tv_output_account.setText(item2[which]);
							dialog.dismiss();
						}
					});
			dialog.setNegativeButton("取消", null);
			dialog.show();
			break;

		case R.id.rl_output_time:
			final Calendar c = Calendar.getInstance();
			
			DatePickerDialog dialog = new DatePickerDialog(this,
					new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							/*c.set(year, monthOfYear, dayOfMonth);*/
							tv_output_time.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
						}
					}, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			dialog.show();
			break;
		case R.id.bt_contain:
		case R.id.iv_contain:
		case R.id.tv_contain:
			double money = Double.parseDouble(et_output_money.getText().toString());
			String type = tv_output_type.getText().toString();
			String account = tv_output_account.getText().toString();
			String date = tv_output_time.getText().toString();
			String[] time = date.split("-");
			String notes = et_notes.getText().toString();
			int year = Integer.parseInt(time[0]);
			int month = Integer.parseInt(time[1]);
			int day = Integer.parseInt(time[2]);
			int week=0;
			try {
				week = DateUtil.getWeek(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.d(TAG, money+"");
			Log.d(TAG, type);
			Log.d(TAG, account);
			Log.d(TAG, date);
			Log.d(TAG, week+"");
			Log.d(TAG, year+"-"+month+"-"+day);
			if(money <= 0){
				Toast.makeText(this, "金额不能为0", 0).show();
				
			}else{
				//将数据保存导数据库并且存储支出总额
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues value = new ContentValues();
				value.put("output_money", money);
				value.put("output_account", account);
				value.put("output_notes", notes);
				value.put("output_year", year);
				value.put("output_month", month);
				value.put("output_week", week);
				value.put("output_date",day);
				value.put("output_type", type);
				db.insert("OutputAccount", null, value);
				Editor editor = sp.edit();
				float opMoney = sp.getFloat("output", 0);
				float output = (float) (money + opMoney);
				editor.putFloat("output", output);
				editor.commit();
				Toast.makeText(this, "保存成功", 0).show();
				finish();
				
			}
			break;
			default:
				break;
		}

	}
}
