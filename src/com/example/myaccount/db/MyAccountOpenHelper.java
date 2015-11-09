package com.example.myaccount.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyAccountOpenHelper extends SQLiteOpenHelper {
	
	public static final String CREATE_INPUT_ACCOUNT = "create table InputAccount ("
			+ "id integer primary key autoincrement,"
			+ "input_money real,"
			+ "input_account text,"
			+ "input_notes text,"
			+ "input_year integer,"
			+ "input_month integer,"
			+ "input_week integer,"
			+ "input_date integer,"
			+ "input_type text)";
	
	public static final String CREATE_OUTPUT_ACCOUNT = "create table OutputAccount("
			+ "id integer primary key autoincrement,"
			+ "output_money real,"
			+ "output_account text,"
			+ "output_notes text,"
			+ "output_year integer,"
			+ "output_month integer,"
			+ "output_week integer,"
			+ "output_date integer,"
			+ "output_type text)";
	public MyAccountOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//创建收入表
		db.execSQL(CREATE_INPUT_ACCOUNT);
		//创建支出表
		db.execSQL(CREATE_OUTPUT_ACCOUNT);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
