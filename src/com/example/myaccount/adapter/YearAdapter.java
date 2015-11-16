package com.example.myaccount.adapter;

import java.util.List;

import com.example.myaccount.R;
import com.example.myaccount.model.YearConsume;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class YearAdapter extends BaseExpandableListAdapter {
	
	private static final String TAG = "BaseExpandableListAdapter";
	private List<String[]> mGroupData;
	private List<List<YearConsume>> mChildData;
	private int groupLayout;
	private int childLayout;
	
	private LayoutInflater mInflater;
	
	public YearAdapter(Context context, int groupLayout,int childLayout,
			List<String[] > mGroupData, List<List<YearConsume>>mChildData) {
		mInflater = (LayoutInflater) context  
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		this.mChildData = mChildData;
		this.mGroupData = mGroupData;
		this.groupLayout = groupLayout;
		this.childLayout = childLayout;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return mGroupData.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return mChildData.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return mGroupData.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return mChildData.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view;
		if(convertView == null){
			view = mInflater.inflate(groupLayout, null);
		}else{
			view = convertView;
		}
		
		TextView tv_groups_day = (TextView) view.findViewById(R.id.tv_groups_day);
		TextView tv_today_money = (TextView) view.findViewById(R.id.tv_today_money);
		tv_groups_day.setText(mGroupData.get(groupPosition)[0]);
		tv_today_money.setText(mGroupData.get(groupPosition)[1]);
		return view;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = mInflater.inflate(childLayout, null);
		ImageView iv_type = (ImageView) view.findViewById(R.id.iv_type);
		TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
		TextView tv_money = (TextView) view.findViewById(R.id.tv_money);
		TextView tv_day = (TextView) view.findViewById(R.id.tv_day);
		int day = mChildData.get(groupPosition).get(childPosition).getDay();
		String str = ""+day;
		if(day<10){
			str = "0"+day;
		}
		iv_type.setImageResource(mChildData.get(groupPosition).get(childPosition).getImageId());
		tv_type.setText(mChildData.get(groupPosition).get(childPosition).getName());
		tv_money.setText(mChildData.get(groupPosition).get(childPosition).getMoney());
		tv_day.setText(str);
		switch(mChildData.get(groupPosition).get(childPosition).getName()){
		case "工资收入":
		case "利息收入":
		case "加班收入":
		case "奖金收入":
		case "投资收入":
		case "兼职收入":
		case "其他收入":
			tv_money.setTextColor(Color.parseColor("#dd2c00"));
			break;
		}
		
		return view;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
