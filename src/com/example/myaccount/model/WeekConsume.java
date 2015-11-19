package com.example.myaccount.model;

import java.io.Serializable;

public class WeekConsume implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String name;
	private String money;
	private int imageId;
	private int day;
	private int id;

	public WeekConsume(String name, String money, int imageId, int day,int id) {
		this.name = name;
		this.money = money;
		this.imageId = imageId;
		this.day = day;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public int getDay() {
		return day;
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
