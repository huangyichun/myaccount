package com.example.myaccount.model;

import java.io.Serializable;

public class DayConsume implements Serializable{

	private String type;
	private String account;
	private String money;
	private int id;
	public DayConsume(String type, String account, String money,int id) {
		super();
		this.type = type;
		this.account = account;
		this.money = money;
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public String getAccount() {
		return account;
	}
	public String getMoney() {
		return money;
	}
	public int getId(){
		return id;
	}
	
	
}
