package com.example.myaccount.model;

public class ShowOutputAccount {

	private String name;
	private String money;
	private int imageId;
	
	public ShowOutputAccount(String name, String money,int imageId){
		this.name = name;
		this.money = money;
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public String getMoney() {
		return money;
	}

	public int getImageId() {
		return imageId;
	}
	
	
}
