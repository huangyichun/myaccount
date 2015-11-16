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
 * ����ListView �� adapter
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
		/*{ "��������", "��Ϣ����", "�Ӱ�����", "��������", "Ͷ������",
			"��ְ����", "��������" }*/
		switch(account.getType()){
		case "��������":
		case "��Ϣ����":
		case "�Ӱ�����":
		case "��������":
		case "Ͷ������":
		case "��ְ����":
		case "��������":
			tv_money.setTextColor(Color.parseColor("#e51c23"));
			break;
			default :
				break;
		}
		
		return view;
	}


	

}
