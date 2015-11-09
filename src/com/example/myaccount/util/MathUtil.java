package com.example.myaccount.util;

import java.text.DecimalFormat;

public class MathUtil {
	public static String setTwoPoint(double d){
		DecimalFormat df = new DecimalFormat("0.00");
		
		return df.format(d);
	}
}
