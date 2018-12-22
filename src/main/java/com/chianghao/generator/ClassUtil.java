package com.chianghao.generator;

import java.lang.reflect.Field;

public class ClassUtil {

	public static Object getFieldValue(Object bean, Field field) {
		field.setAccessible(true);
		try {
			return field.get(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static Object getFieldValue(Object bean, String fieldName) {
		Field field=null;
		try {
			field = bean.getClass().getDeclaredField(fieldName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		if(field==null) {
			return null;
		}
		return getFieldValue(bean,field);
	}
	
	
}
