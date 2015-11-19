package com.example.myaccount.activity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.myaccount.R;
import com.example.myaccount.adapter.WeekAdapter;
import com.example.myaccount.db.MyAccountOpenHelper;
import com.example.myaccount.model.DayConsume;
import com.example.myaccount.model.WeekConsume;
import com.example.myaccount.util.DateUtil;
import com.example.myaccount.util.MathUtil;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WeekExpandableListViewActivity extends ExpandableListActivity implements OnClickListener
		 {

	private static final String TAG = "WeekExpandableListViewActivity";
	private MyAccountOpenHelper dbHelper;
	private int month;
	private int year;
	private int day;
	private int week;
	private ImageView iv_back;
	private WeekAdapter adapter;
	private ImageView iv_update;
	private TextView set_budget;
	private TextView tv_yiyong_1;
	private TextView tv_keyong;
	private RelativeLayout rl_none_data;
	private double inputMoney = 0;
	private double outputMoney = 0;
	private TextView tv_back;
	// 创建存储grops数据容器
	private List<String> group = new ArrayList<String>();

	// 创建存储child数据容器
	private List<List<WeekConsume>> child = new ArrayList<List<WeekConsume>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.week_mian_layout);
		// 初始化控件
		initView();
	
		
	}

	private void setView() {
		tv_yiyong_1.setText("" + MathUtil.setTwoPoint(inputMoney));
		tv_keyong.setText("" + MathUtil.setTwoPoint(outputMoney));
		set_budget.setText("" + MathUtil.setTwoPoint(inputMoney - outputMoney));
		try {
			tv_back.setText(DateUtil.getStWeekEnd(year, month, day,1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		iv_back.setOnClickListener(this);
		iv_update.setOnClickListener(this);
		tv_back.setOnClickListener(this);
		
		
	}

	// 初始化控件
	private void initView() {

		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_update = (ImageView) findViewById(R.id.iv_update);
		set_budget = (TextView) findViewById(R.id.set_budget);
		tv_yiyong_1 = (TextView) findViewById(R.id.tv_yiyong_1);
		tv_keyong = (TextView) findViewById(R.id.tv_keyong);
		rl_none_data = (RelativeLayout) findViewById(R.id.rl_none_data);
		rl_none_data = (RelativeLayout) findViewById(R.id.rl_none_data);
		tv_back = (TextView) findViewById(R.id.tv_back);
	}

	private void initData() {
		// 查询数据

		List<WeekConsume> list = new ArrayList<>();
		// 存放有数据的日期
		int[] days = new int[31];
		try {
			if(DateUtil.isSunday(year, month, day)){
				week = week -1;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 查询数据库
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("OutputAccount", null,
				"output_year = ? and output_month = ? and output_week = ?",
				new String[] { "" + year, "" + month, "" + week }, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				int imageId;
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				int tDay = cursor.getInt(cursor.getColumnIndex("output_date"));
				String type = cursor.getString(cursor
						.getColumnIndex("output_type"));
				double money = cursor
						.getFloat(cursor.getColumnIndex("output_money"));
				outputMoney = money + outputMoney;
				switch (type) {
				case "食品酒水":
					imageId = R.drawable.icon_spjs;
					break;
				case "衣服饰品":
					imageId = R.drawable.icon_yfsp;
					break;
				case "居家物业":
					imageId = R.drawable.icon_jjwy;
					break;
				case "行车交通":
					imageId = R.drawable.icon_xcjt_sjcfy;
					break;
				case "交流通讯":
					imageId = R.drawable.icon_jltx;
					break;
				case "休闲娱乐":
					imageId = R.drawable.icon_xxyl;
					break;
				case "学习进修":
					imageId = R.drawable.icon_xxjx;
					break;
				case "医疗保健":
					imageId = R.drawable.icon_rqwl;
					break;
				case "金融保险":
					imageId = R.drawable.icon_ylbj;
					break;
				case "人情往来":
					imageId = R.drawable.icon_jrtz_bxsr;
					break;
				default:
					imageId = R.drawable.icon_zysr_jzsr;
					break;
				}

				WeekConsume wc = new WeekConsume(type, "" + MathUtil.setTwoPoint(money), imageId,
						tDay, id);
				list.add(wc);
				days[tDay] = tDay;

			} while (cursor.moveToNext());

		}

		cursor = db.query("InputAccount", null,
				"input_year = ? and input_month = ? and input_week = ?",
				new String[] { "" + year, "" + month, "" + week }, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				int imageId;
				int id = cursor.getInt(cursor.getColumnIndex("id"));
				int tDay = cursor.getInt(cursor.getColumnIndex("input_date"));
				String type = cursor.getString(cursor
						.getColumnIndex("input_type"));
				double money = cursor.getFloat(cursor.getColumnIndex("input_money"));
				inputMoney = money + inputMoney;
				switch (type) {
				case "工资收入":
					imageId = R.drawable.icon_zysr_gzsr;
					break;
				case "利息收入":
					imageId = R.drawable.icon_zysr_lxsr;
					break;
				case "加班收入":
					imageId = R.drawable.icon_zysr_jbsr;
					break;
				case "奖金收入":
					imageId = R.drawable.icon_zysr_jjsr;
					break;
				case "投资收入":
					imageId = R.drawable.icon_zysr_tzsr;
					break;
				case "兼职收入":
					imageId = R.drawable.icon_zysr_jzsr;
					break;
				case "其他收入":
					imageId = R.drawable.icon_yyfy_qtsr;
					break;
				default:
					imageId = R.drawable.icon_yyfy_qtsr;
					break;
				}

				WeekConsume wc = new WeekConsume(type, "" + MathUtil.setTwoPoint(money), imageId,
						tDay, id);
				list.add(wc);

				days[tDay] = tDay;

			} while (cursor.moveToNext());
		}
		cursor.close();
		// 设置group和child中的数据
		for (int i = 0; i < days.length; i++) {
			if (days[i] != 0) {
				List<WeekConsume> childData = new ArrayList<WeekConsume>();
				for (WeekConsume weekConsume : list) {
					if (weekConsume.getDay() == i) {
						childData.add(weekConsume);
					}

				}
				String str = year + "-" + month + "-" + i;
				group.add(str);
				child.add(childData);
			}
		}

	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Intent intent;
		WeekConsume weekConsume = child.get(groupPosition).get(childPosition);
		String type = weekConsume.getName();
		Log.d(TAG, weekConsume.getMoney());
		Log.d(TAG, type);
		switch (type) {
		case "工资收入":
		case "利息收入":
		case "加班收入":
		case "奖金收入":
		case "投资收入":
		case "兼职收入":
		case "其他收入":
			 intent = new Intent(WeekExpandableListViewActivity.this,
					InputModifyActivity.class);
			intent.putExtra("WeekConsume", weekConsume);
			startActivity(intent);
			break;
		default:
			 intent = new Intent(WeekExpandableListViewActivity.this,
					OutputModifyActivity.class);
			intent.putExtra("WeekConsume", weekConsume);
			startActivity(intent);
			break;
		}
		
		return super.onChildClick(parent, v, groupPosition, childPosition, id);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_back:
		case R.id.iv_back:
			finish();
			break;
			
		case R.id.iv_update:
			Intent intent = new Intent(this, NewOutputActivity.class);
			startActivity(intent);
			break;
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		inputMoney = 0;
		outputMoney = 0;
		group.clear();
		child.clear();
		dbHelper = new MyAccountOpenHelper(this, "Account.db", null, 1);
		// 初始化时间
		year = DateUtil.getYear();
		month = DateUtil.getMonth();
		day = DateUtil.getDay();
		try {
			week = DateUtil.getWeek(year + "-" + month + "-" + day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 初始化数据
		initData();
		if(group.isEmpty()){
			rl_none_data.setVisibility(View.VISIBLE);
		}else{
			rl_none_data.setVisibility(View.GONE);
		}
		// 设置控件
		setView();
		
		// 去掉显示的箭头
		ExpandableListView view = getExpandableListView();
		view.setGroupIndicator(null);

		adapter = new WeekAdapter(WeekExpandableListViewActivity.this,
				R.layout.week_groups_layout, R.layout.week_children_layout,
				group, child);
		setListAdapter(adapter);
		for(int i = 0; i < adapter.getGroupCount(); i++){  
            
			   view.expandGroup(i);  
			                          
			}
		
	}

}
