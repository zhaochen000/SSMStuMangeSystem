package com.woniu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class MyDateConvert implements Converter<String, Date> {
	String formatStr = "yyyy-MM-dd";
	String formatStr2 = "yyyy/MM/dd";
	@Override
	public Date convert(String source) {
		//source��ֵ�ǣ�1999-11-11
		//��������ת���ĸ�ʽ����
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date d = null;
		try {
			d = sdf.parse(source);
		} catch (ParseException e) {
			sdf = new SimpleDateFormat(formatStr2);
			try {
				d = sdf.parse(source);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return d;
	}

}
