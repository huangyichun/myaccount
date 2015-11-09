package com.example.myaccount.model;

public class ConsumeBudge {

	private int imageId;
	private String name;
	private int money;
	
	public ConsumeBudge(String name,int imageId,int money){
		this.name = name;
		this.imageId = imageId;
		this.money = money;
				
	}

	public int getImageId() {
		return imageId;
	}

	public String getName() {
		return name;
	}

	public int getMoney() {
		return money;
	}
	
	
}
