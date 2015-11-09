package com.example.myaccount.adapter;

import java.util.List;

import com.example.myaccount.R;
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
 * …Ë÷√ListView µƒ adapter
 * @author Administrator
 *
 */
public class BudgetAdapter extends ArrayAdapter<ShowOutputAccount> {

	private int resourcedId;

	public BudgetAdapter(Context context,  int textViewResourceId,
			List<ShowOutputAccount> objects) {
		super(context, textViewResourceId, objects);
		resourcedId = textViewResourceId;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ShowOutputAccount account = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourcedId, null);
	
		
			
		ImageView iv_type = (ImageView) view.findViewById(R.id.iv_type);
		TextView tv_type = (TextView) view.findViewById(R.id.tv_type);
		TextView tv_money = (TextView) view.findViewById(R.id.tv_money);
		
		iv_type.setImageResource(account.getImageId());
		tv_type.setText(account.getName());
		tv_money.setText(account.getMoney());
		
		
		
	
		if(!("0.00".equals(account.getMoney()))){
			tv_money.setTextColor(Color.parseColor("#e51c23"));
		}
		return view;
	}


	

}
