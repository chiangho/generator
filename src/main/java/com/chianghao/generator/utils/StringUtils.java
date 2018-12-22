package com.chianghao.generator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static boolean isEmpty(String content){
		if(content==null||content.length()==0) {
			return true;
		}
		return false;
	}
	
	
	public static final char UNDERLINE = '_';
    /**
     * 驼峰格式字符串转换为下划线格式字符串
     * 
     * @param param
     * @return
     */
    public static String camelToUnderline(String param,boolean smallCamel) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
            	 if(smallCamel||i>0) {
            		 sb.append(UNDERLINE);
                 }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串
     * 
     * @param param
     * @return
     */
    public static String underlineToCamel(String param,boolean smallCamel) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if(!smallCamel&&i==0) {
            	c=Character.toUpperCase(c);
            }
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线格式字符串转换为驼峰格式字符串2
     * 
     * @param param
     * @return
     */
    public static String underlineToCamel2(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("_").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 首字母大写
     * @param content
     * @return
     */
    public static String firstCharacterUpperCase(String content) {
    	if(isEmpty(content)) {
    		return "";
    	}
    	return  content.substring(0, 1).toUpperCase() + content.substring(1);
    }
    /**
     * 首字母小写
     * @param content
     * @return
     */
    public static String firstCharacterLowerCase(String content) {
    	if(isEmpty(content)) {
    		return "";
    	}
    	return  content.substring(0, 1).toLowerCase() + content.substring(1);
    }
    
    
    public static void main(String[] args) {
        String aaa = "app";
        System.out.println(underlineToCamel(aaa,false));
        System.out.println(underlineToCamel2(aaa));
        aaa = "AppVersionFld";
        System.out.println(camelToUnderline(aaa,false));
    
    }
	
}
