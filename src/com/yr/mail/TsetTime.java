package com.yr.mail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TsetTime {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dstr="2019-1-2 00:00:00 ";
		java.util.Date date=sdf.parse(dstr);
		long  s1=date.getTime();//将时间转为毫秒
		long s2=System.currentTimeMillis();//得到当前的毫秒
		long  day=(s2-s1)/1000/60/60/24;
		System.out.println("距现在已有"+day+"天，你得抓紧时间学习了" );
	}
}
