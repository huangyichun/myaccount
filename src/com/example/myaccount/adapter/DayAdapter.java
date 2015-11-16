package com.example.myaccount.adapter;

import java.util.List;

import com.example.myaccount.R;
import com.example.myaccount.model.DayConsume;
import com.example.myaccount.model.OutputAccount;
import com.example.myaccount.model.ShowOutputAccount;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 设置ListView 的 adapter
 * @author Administrator
 *
 */
public class DayAdapter extends ArrayAdapter<DayConsume> {

	private int resourcedId;

	public DayAdapter(Context context,  int textViewResourceId,
			List<DayConsume> objects) {
		super(context, textViewResourceId, objects);
		resourcedId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DayConsume account = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourcedId, null);
	
		
			
		
		TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
		TextView tv_money = (TextView) view.findViewById(R.id.tv_money);
		TextView tv_account = (TextView) view.findViewById(R.id.tv_account);
		
		tv_account.setText(account.getAccount());
		tv_type.setText(account.getType());
		tv_money.setText(account.getMoney());
		/*{ "工资收入", "利息收入", "加班收入", "奖金收入", "投资收入",
			"兼职收入", "其他收入" }*/
		switch(account.getType()){
		case "工资收入":
		case "利息收入":
		case "加班收入":
		case "奖金收入":
		case "投资收入":
		case "兼职收入":
		case "其他收入":
			tv_money.setTextColor(Color.parseColor("#e51c23"));
			break;
			default :
				break;
		}
		
		return view;
	}


	

}
