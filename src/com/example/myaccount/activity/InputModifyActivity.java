package com.example.myaccount.activity;

import java.text.ParseException;
import java.util.Calendar;

import com.example.myaccount.R;
import com.example.myaccount.db.MyAccountOpenHelper;
import com.example.myaccount.model.DayConsume;
import com.example.myaccount.model.WeekConsume;
import com.example.myaccount.model.YearConsume;
import com.example.myaccount.util.DateUtil;
import com.example.myaccount.util.MathUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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

public class InputModifyActivity extends Activity implements OnClickListener {

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
	private int id;
	private Button bt_dele;
	private Double money;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.input_modify_layout);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		dbHelper = new MyAccountOpenHelper(this, "Account.db", null, 1);

		// 初始化控件
		init();

		DayConsume dayConsume = (DayConsume) getIntent().getSerializableExtra(
				"dayConsume");
		if(dayConsume != null){
			id = dayConsume.getId();
		}
		
		
		WeekConsume weekConsume = (WeekConsume) getIntent().getSerializableExtra("WeekConsume");
		if(weekConsume != null){
			id = weekConsume.getId();
		}
		
		YearConsume yearConsume = (YearConsume) getIntent().getSerializableExtra("YearConsume");
		if(yearConsume != null){
			id = yearConsume.getId();
		}
		
		Log.d(TAG, id + "");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("InputAccount", null, "id = ? ",
				new String[] { "" + id }, null, null, null);

		if (cursor.moveToFirst()) {
			Log.d(TAG, "运行到这里了3");

			String type = cursor.getString(cursor.getColumnIndex("input_type"));
			money = (double) cursor.getFloat(cursor
					.getColumnIndex("input_money"));
			String account = cursor.getString(cursor
					.getColumnIndex("input_account"));
			String notes = cursor.getString(cursor
					.getColumnIndex("input_notes"));
			int year = cursor.getInt(cursor.getColumnIndex("input_year"));
			int month = cursor.getInt(cursor.getColumnIndex("input_month"));
			int day = cursor.getInt(cursor.getColumnIndex("input_date"));

			et_output_money.setText(MathUtil.setTwoPoint(money));
			tv_output_type.setText(type);
			tv_output_account.setText(account);
			tv_output_time.setText(year + "-" + month + "-" + day);

			et_notes.setText(notes);

		}
		cursor.close();

		iv_back.setOnClickListener(this);
		rl_new_output.setOnClickListener(this);
		et_output_money.setOnClickListener(this);
		rl_output_type.setOnClickListener(this);
		rl_output_account.setOnClickListener(this);
		rl_output_time.setOnClickListener(this);
		tv_contain.setOnClickListener(this);
		bt_contain.setOnClickListener(this);
		iv_contain.setOnClickListener(this);

		bt_dele.setOnClickListener(this);
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
		bt_dele = (Button) findViewById(R.id.bt_dele);
		tv_output_time.setText(DateUtil.getDate());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back:

			finish();
			break;
		case R.id.rl_new_output:
			intent = new Intent(InputModifyActivity.this,
					NewOutputActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.et_output_money:
			et_output_money.setText("");

			break;

		case R.id.rl_output_type:
			final String item1[] = { "工资收入	", "利息收入", "加班收入", "奖金收入", "投资收入",
					"兼职收入", "其他收入" };
			dialog = new AlertDialog.Builder(InputModifyActivity.this);
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
			dialog = new AlertDialog.Builder(InputModifyActivity.this);
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
							tv_output_time.setText(year + "-"
									+ (monthOfYear + 1) + "-" + dayOfMonth);
						}
					}, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			dialog.show();
			break;
		case R.id.bt_contain:
		case R.id.iv_contain:
		case R.id.tv_contain:
			String str = et_output_money.getText().toString();

			if (TextUtils.isEmpty(str)) {
				Toast.makeText(InputModifyActivity.this, "输入的金额不能为0",
						Toast.LENGTH_LONG).show();
				return;
			}
			double money1 = Double.parseDouble(str);
			String type = tv_output_type.getText().toString();
			String account = tv_output_account.getText().toString();
			String date = tv_output_time.getText().toString();
			String[] time = date.split("-");
			String notes = et_notes.getText().toString();
			int year = Integer.parseInt(time[0]);
			int month = Integer.parseInt(time[1]);
			int day = Integer.parseInt(time[2]);
			int week = 0;
			
			try {
				week = DateUtil.getWeek(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(DateUtil.isSunday(year, month, day)){
					week = week -1;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (money1 <= 0) {
				Toast.makeText(this, "金额不能为0", 0).show();

			} else {
				// 将数据保存导数据库并且存储支出总额
				Log.d(TAG, money + "");
				Log.d(TAG, type);
				Log.d(TAG, account);
				Log.d(TAG, date);
				Log.d(TAG, year + "-" + month + "-" + day);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues value = new ContentValues();
				value.put("input_money", money);
				value.put("input_account", account);
				value.put("input_notes", notes);
				value.put("input_year", year);
				value.put("input_month", month);
				
				value.put("input_week", week);
				value.put("input_date", day);
				value.put("input_type", type);
				db.insert("InputAccount", null, value);
			
				Toast.makeText(this, "保存成功", 0).show();

				finish();

			}
			break;
		case R.id.bt_dele:
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			db.delete("InputAccount", "id = ?", new String[] { "" + id });
		
			Toast.makeText(InputModifyActivity.this, "删除成功", Toast.LENGTH_LONG)
					.show();
			finish();
		default:
			break;
		}

	}

}
